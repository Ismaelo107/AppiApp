package com.ismaelo.apiapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ismaelo.apiapp.core.Constants
import com.ismaelo.apiapp.data.local.LocalDatasource
import com.ismaelo.apiapp.data.local.Movie
import com.ismaelo.apiapp.data.remote.MovieDTO
import com.ismaelo.apiapp.data.remote.MovieResponse
import com.ismaelo.apiapp.data.remote.RetrofitBuilder.apiService
import com.ismaelo.apiapp.data.remote.toLocalMovie
import com.ismaelo.apiapp.ui.util.ScreenState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieViewModel(private val localDatasource: LocalDatasource) : ViewModel() {

    // ========================================
    // VIEWMODEL REMOTO (API)
    // ========================================

    private val _movieDetail = MutableStateFlow<MovieDTO?>(null)
    val movieDetail: StateFlow<MovieDTO?> = _movieDetail.asStateFlow()


    private val _popularMovies = MutableStateFlow<List<MovieDTO>>(emptyList())
    val popularMovies: StateFlow<List<MovieDTO>> = _popularMovies.asStateFlow()

    private val _nowPlayingMovies = MutableStateFlow<List<MovieDTO>>(emptyList())
    val nowPlayingMovies: StateFlow<List<MovieDTO>> = _nowPlayingMovies.asStateFlow()

    private val _topRatedMovies = MutableStateFlow<List<MovieDTO>>(emptyList())
    val topRatedMovies: StateFlow<List<MovieDTO>> = _topRatedMovies.asStateFlow()

    private val _upcomingMovies = MutableStateFlow<List<MovieDTO>>(emptyList())
    val upcomingMovies: StateFlow<List<MovieDTO>> = _upcomingMovies.asStateFlow()

    // Estado para el proceso de carga
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    // Estado de la UI (para manejar errores)
    private val _uiState = MutableStateFlow<ScreenState>(ScreenState.Loading)
    val uiState: StateFlow<ScreenState> = _uiState.asStateFlow()

    // Manejador para capturar excepciones
    private val handler = CoroutineExceptionHandler { _, exception ->
        _uiState.value = ScreenState.Error("Error: ${exception.localizedMessage}")
    }

    // Función para obtener las películas populares desde la API
    fun fetchPopularMovies() = fetchMovies(call = { apiService.getPopular(Constants.API_KEY) },
        updateState = { _popularMovies.value = it })

    // Función para obtener las películas en cartelera (Now Playing)
    fun fetchNowPlayingMovies() =
        fetchMovies(call = { apiService.nowPlayingMovies(Constants.API_KEY) },
            updateState = { _nowPlayingMovies.value = it })

    // Función para obtener las películas más valoradas (Top Rated)
    fun fetchTopRatedMovies() = fetchMovies(call = { apiService.getTopRated(Constants.API_KEY) },
        updateState = { _topRatedMovies.value = it.sortedByDescending { movie -> movie.rating } })

    // Función para obtener las próximas películas (Upcoming)
    fun fetchUpcomingMovies() = fetchMovies(call = { apiService.getUpComing(Constants.API_KEY) },
        updateState = { _upcomingMovies.value = it })

    // Función general para manejar la obtención de películas desde la API
    private fun fetchMovies(
        call: suspend () -> Response<MovieResponse>, updateState: (List<MovieDTO>) -> Unit
    ) = viewModelScope.launch(handler) {
        _isLoading.emit(true)
        try {
            val response = call()
            if (response.isSuccessful) {
                val movies = response.body()?.results ?: emptyList()
                updateState(movies)
                _uiState.emit(ScreenState.Success(movies))
            } else {
                _uiState.emit(ScreenState.Error("Error al obtener películas desde el servidor."))
            }
        } catch (e: Exception) {
            _uiState.emit(ScreenState.Error("Error de red: ${e.localizedMessage}"))
        } finally {
            _isLoading.emit(false)
        }
    }

    // Función para obtener los detalles de una película desde la API usando su ID
    fun fetchMovieById(movieId: String) = viewModelScope.launch(handler) {
        _isLoading.emit(true)
        try {
            Log.d("MovieViewModel", "Buscando película con ID: $movieId") // 📌 Log de ID

            val response = apiService.getMovieById(movieId, Constants.API_KEY)
            Log.d("MovieViewModel", "Respuesta de la API: $response") // 📌 Log de respuesta

            val movieResponse = response.body()
            Log.d("MovieViewModel", "Cuerpo de la respuesta: $movieResponse") // 📌 Log de body

            if (response.isSuccessful && movieResponse != null) {
                val movieDTO = MovieDTO(
                    id = movieResponse.id,
                    title = movieResponse.title,
                    overview = movieResponse.overview,
                    image = movieResponse.image ?: "",
                    popularity = movieResponse.popularity,
                    rating = movieResponse.rating
                )
                Log.d("MovieViewModel", "Película encontrada: $movieDTO") // 📌 Log de película

                _movieDetail.emit(movieDTO)
                _uiState.emit(ScreenState.Success(movieDTO))
            } else {
                val errorMessage = "Error al obtener detalles: ${response.message()}"
                Log.e("MovieViewModel", errorMessage) // ❌ Error de la API
                _uiState.emit(ScreenState.Error(errorMessage))
            }
        } catch (e: Exception) {
            val errorMessage = "Error de red: ${e.localizedMessage}"
            Log.e("MovieViewModel", errorMessage) // ❌ Error de red
            _uiState.emit(ScreenState.Error(errorMessage))
        } finally {
            _isLoading.emit(false)
        }
    }

    // ========================================
    // VIEWMODEL LOCAL (ROOM)
    // ========================================

    private val _movieDetailFromLocal = MutableStateFlow<Movie?>(null)
    val movieDetailFromLocal: StateFlow<Movie?> = _movieDetailFromLocal.asStateFlow()



    // Esto es en tu ViewModel, asegúrate de que se emita correctamente
    // Función para obtener una película específica desde la base de datos local
    fun fetchMovieFromLocalById(movieId: String) = viewModelScope.launch(handler) {
        try {
            // Obtenemos la película específica por su ID
            val movie = localDatasource.getById(movieId)
            Log.d("movielocal", "Película encontrada en la base de datos local: $movie")

            // Si la película existe, emitimos el resultado
            if (movie != null) {
                _movieDetailFromLocal.emit(movie) // Emitimos la película encontrada
                _uiState.emit(ScreenState.Success(movie)) // Emitimos el estado de éxito
            } else {
                // Si no se encuentra la película, emitimos un error
                _uiState.emit(ScreenState.Error("Película no encontrada en la base de datos local."))
            }
        } catch (e: Exception) {
            // Capturamos cualquier error y lo mostramos
            val errorMessage = "Error al obtener película desde la base de datos local: ${e.localizedMessage}"
            Log.e("MovieViewModel", errorMessage)
            _uiState.emit(ScreenState.Error(errorMessage))
        }
    }





    // Función para guardar una película en la base de datos local
    fun saveMovieToLocal(movieDTO: MovieDTO) = viewModelScope.launch(handler) {
        try {
            localDatasource.insert(movieDTO.toLocalMovie())
        } catch (e: Exception) {
            _uiState.emit(ScreenState.Error("Error al guardar película: ${e.localizedMessage}"))
        }
    }

    // Función para eliminar una película de la base de datos local
    fun deleteMovieFromLocal(movie: Movie) = viewModelScope.launch(handler) {
        try {
            localDatasource.delete(movie)
            fetchFavoriteMovies() // Refrescar la lista de favoritos después de eliminar
        } catch (e: Exception) {
            _uiState.emit(ScreenState.Error("Error al eliminar película: ${e.localizedMessage}"))
        }
    }

    // Función para obtener las películas favoritas desde la base de datos local
    fun fetchFavoriteMovies() = viewModelScope.launch(handler) {
        try {
            val favoriteMovies = localDatasource.getAll()
            _favoriteMovies.emit(favoriteMovies)
        } catch (e: Exception) {
            _uiState.emit(ScreenState.Error("Error al obtener favoritos: ${e.localizedMessage}"))
        }
    }

    // Estado para las películas favoritas en la base de datos local
    private val _favoriteMovies = MutableStateFlow<List<Movie>>(emptyList())
    val favoriteMovies: StateFlow<List<Movie>> = _favoriteMovies.asStateFlow()
}
