package com.ismaelo.apiapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ismaelo.apiapp.data.local.LocalDatasource

@Suppress("UNCHECKED_CAST")
class MovieViewModelFactory(
    private val localDatasource: LocalDatasource
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(localDatasource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
