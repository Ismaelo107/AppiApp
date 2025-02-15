package com.ismaelo.apiapp.ui.util

sealed class ScreenState {
    object Loading : ScreenState()
    data class Success<T>(val data: T) : ScreenState()
    data class Error(val message: String) : ScreenState()
}
