package com.ismaelo.apiapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ismaelo.apiapp.core.Constants
import com.ismaelo.apiapp.data.remote.RetrofitBuilder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    private val _popularMovies = MutableStateFlow<List<String>>(emptyList())
    val popularMovies: StateFlow<List<String>> = _popularMovies

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun fetchPopularMovies() {
        _isLoading.value = true

        viewModelScope.launch {
            try {
                val response = RetrofitBuilder.apiService.getPopular(Constants.API_KEY)
                if (response.isSuccessful) {
                    Log.d("API_RESPONSE", "Respuesta exitosa: ${response.body()}")
                    _popularMovies.value = response.body()?.results?.map { it.title } ?: emptyList()
                    _errorMessage.value = null
                } else {
                    val error = "Error en la respuesta: ${response.message()} (${response.code()})"
                    Log.e("API_ERROR", error)
                    _errorMessage.value = error
                    _popularMovies.value = emptyList()
                }
            } catch (e: Exception) {
                val error = "Excepción en la solicitud: ${e.localizedMessage}"
                Log.e("API_ERROR", error)
                _errorMessage.value = error
                _popularMovies.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }
}
