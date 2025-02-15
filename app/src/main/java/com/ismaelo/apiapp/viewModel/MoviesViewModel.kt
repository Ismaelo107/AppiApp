package com.ismaelo.apiapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ismaelo.apiapp.core.Constants
import com.ismaelo.apiapp.data.remote.MovieDTO
import com.ismaelo.apiapp.data.remote.RetrofitBuilder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    private val _popularMovies = MutableStateFlow<List<MovieDTO>>(emptyList())
    val popularMovies: StateFlow<List<MovieDTO>> = _popularMovies

    private val _nowPlayingMovies = MutableStateFlow<List<MovieDTO>>(emptyList())
    val nowPlayingMovies: StateFlow<List<MovieDTO>> = _nowPlayingMovies

    private val _topRatedMovies = MutableStateFlow<List<MovieDTO>>(emptyList())
    val topRatedMovies: StateFlow<List<MovieDTO>> = _topRatedMovies

    private val _upcomingMovies = MutableStateFlow<List<MovieDTO>>(emptyList()) // Nuevo estado para las películas próximas
    val upcomingMovies: StateFlow<List<MovieDTO>> = _upcomingMovies

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun fetchPopularMovies() {
        _isLoading.value = true

        viewModelScope.launch {
            try {
                val response = RetrofitBuilder.apiService.getPopular(Constants.API_KEY)
                if (response.isSuccessful) {
                    _popularMovies.value = response.body()?.results ?: emptyList()
                } else {
                    _popularMovies.value = emptyList()
                }
            } catch (e: Exception) {
                _popularMovies.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun fetchNowPlayingMovies() {
        _isLoading.value = true

        viewModelScope.launch {
            try {
                val response = RetrofitBuilder.apiService.nowPlayingMovies(Constants.API_KEY)
                if (response.isSuccessful) {
                    _nowPlayingMovies.value = response.body()?.results ?: emptyList()
                } else {
                    _nowPlayingMovies.value = emptyList()
                }
            } catch (e: Exception) {
                _nowPlayingMovies.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun fetchTopRatedMovies() {
        _isLoading.value = true

        viewModelScope.launch {
            try {
                val response = RetrofitBuilder.apiService.getTopRated(Constants.API_KEY)
                if (response.isSuccessful) {
                    _topRatedMovies.value = response.body()?.results ?: emptyList()
                } else {
                    _topRatedMovies.value = emptyList()
                }
            } catch (e: Exception) {
                _topRatedMovies.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Función para obtener las próximas películas
    fun fetchUpcomingMovies() {
        _isLoading.value = true

        viewModelScope.launch {
            try {
                val response = RetrofitBuilder.apiService.getUpComing(Constants.API_KEY)
                if (response.isSuccessful) {
                    _upcomingMovies.value = response.body()?.results ?: emptyList()
                } else {
                    _upcomingMovies.value = emptyList()
                }
            } catch (e: Exception) {
                _upcomingMovies.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }
}
