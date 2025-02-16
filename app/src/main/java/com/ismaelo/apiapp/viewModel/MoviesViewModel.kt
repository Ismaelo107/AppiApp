package com.ismaelo.apiapp.viewModel

import androidx.compose.material3.Text
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ismaelo.apiapp.core.Constants
import com.ismaelo.apiapp.data.local.LocalDatasource
import com.ismaelo.apiapp.data.local.Movie
import com.ismaelo.apiapp.data.remote.MovieDTO
import com.ismaelo.apiapp.data.remote.RetrofitBuilder
import com.ismaelo.apiapp.data.remote.toLocalMovie
import com.ismaelo.apiapp.ui.util.ScreenState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieViewModel(private val localDatasource: LocalDatasource) : ViewModel() {

    private val _popularMovies = MutableStateFlow<List<MovieDTO>>(emptyList())
    val popularMovies: StateFlow<List<MovieDTO>> = _popularMovies

    private val _nowPlayingMovies = MutableStateFlow<List<MovieDTO>>(emptyList())
    val nowPlayingMovies: StateFlow<List<MovieDTO>> = _nowPlayingMovies

    private val _topRatedMovies = MutableStateFlow<List<MovieDTO>>(emptyList())
    val topRatedMovies: StateFlow<List<MovieDTO>> = _topRatedMovies

    private val _upcomingMovies = MutableStateFlow<List<MovieDTO>>(emptyList())
    val upcomingMovies: StateFlow<List<MovieDTO>> = _upcomingMovies

    private val _favoriteMovies = MutableStateFlow<List<Movie>>(emptyList())
    val favoriteMovies: StateFlow<List<Movie>> = _favoriteMovies.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _uiState: MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState.Loading)
    val uiState: StateFlow<ScreenState> = _uiState.asStateFlow()

    private val handler = CoroutineExceptionHandler { _, exception ->
        _uiState.value = ScreenState.Error("Error: ${exception.localizedMessage}")
    }

    private val _isConnected = MutableStateFlow(true) // Suponemos que hay conexión al inicio
    val isConnected: StateFlow<Boolean> = _isConnected

    fun updateConnectionStatus(status: Boolean) {
        _isConnected.value = status
    }

    fun fetchPopularMovies() {
        _isLoading.value = true
        viewModelScope.launch(handler) {
            try {
                val response = RetrofitBuilder.apiService.getPopular(Constants.API_KEY)
                if (response.isSuccessful) {
                    _popularMovies.value = response.body()?.results ?: emptyList()
                    _uiState.value = ScreenState.Success(_popularMovies.value)
                } else {
                    _popularMovies.value = emptyList()
                    _uiState.value =
                        ScreenState.Error("Error al obtener las películas populares desde el servidor.")
                }
            } catch (e: Exception) {
                // Si no hay internet o algún otro error, cargar desde la base de datos local
                _uiState.value =
                    ScreenState.Error("Error al obtener las películas populares desde el servidor: ${e.localizedMessage}")

            } finally {
                _isLoading.value = false
            }
        }
    }


    fun fetchNowPlayingMovies() {
        _isLoading.value = true
        viewModelScope.launch(handler) {
            try {
                val response = RetrofitBuilder.apiService.nowPlayingMovies(Constants.API_KEY)
                if (response.isSuccessful) {
                    _nowPlayingMovies.value = response.body()?.results ?: emptyList()
                } else {
                    _nowPlayingMovies.value = emptyList()
                }
            } catch (e: Exception) {
                _nowPlayingMovies.value = emptyList()
                _uiState.value =
                    ScreenState.Error("Error al obtener las películas en cartelera: ${e.localizedMessage}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun fetchTopRatedMovies() {
        _isLoading.value = true
        viewModelScope.launch(handler) {
            try {
                val response = RetrofitBuilder.apiService.getTopRated(Constants.API_KEY)
                if (response.isSuccessful) {
                    _topRatedMovies.value = response.body()?.results ?: emptyList()
                } else {
                    _topRatedMovies.value = emptyList()
                }
            } catch (e: Exception) {
                _topRatedMovies.value = emptyList()
                _uiState.value =
                    ScreenState.Error("Error al obtener las películas mejor valoradas: ${e.localizedMessage}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun fetchUpcomingMovies() {
        _isLoading.value = true
        viewModelScope.launch(handler) {
            try {
                val response = RetrofitBuilder.apiService.getUpComing(Constants.API_KEY)
                if (response.isSuccessful) {
                    _upcomingMovies.value = response.body()?.results ?: emptyList()
                } else {
                    _upcomingMovies.value = emptyList()
                }
            } catch (e: Exception) {
                _upcomingMovies.value = emptyList()
                _uiState.value =
                    ScreenState.Error("Error al obtener las películas próximas: ${e.localizedMessage}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun saveMovieToLocal(movieDTO: MovieDTO) {
        val movie = movieDTO.toLocalMovie()
        viewModelScope.launch(handler) {
            try {
                localDatasource.insert(movie)
                _uiState.value = ScreenState.Success(movie)
            } catch (e: Exception) {
                _uiState.value =
                    ScreenState.Error("Error al guardar la película como favorita: ${e.localizedMessage}")
            }
        }
    }

    fun deleteMovieFromLocal(movie: Movie) {
        viewModelScope.launch(handler) {
            try {
                localDatasource.delete(movie)
                _uiState.value = ScreenState.Success(movie)
                fetchFavoriteMovies()
            } catch (e: Exception) {
                _uiState.value =
                    ScreenState.Error("Error al eliminar la película favorita: ${e.localizedMessage}")
            }
        }
    }

    fun fetchFavoriteMovies() {
        viewModelScope.launch(handler) {
            try {
                _favoriteMovies.value = localDatasource.getAll()
                _uiState.value = ScreenState.Success(_favoriteMovies.value)
            } catch (e: Exception) {
                _favoriteMovies.value = emptyList()
                _uiState.value =
                    ScreenState.Error("Error al obtener los favoritos: ${e.localizedMessage}")
            }
        }
    }
}
