package com.ismaelo.apiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.ismaelo.apiapp.data.local.LocalDatasource
import com.ismaelo.apiapp.navigation.AppNavigation
import com.ismaelo.apiapp.ui.theme.ApiAppTheme
import com.ismaelo.apiapp.viewModel.MovieViewModel
import com.ismaelo.apiapp.viewModel.MovieViewModelFactory

class MainActivity : ComponentActivity() {
    private lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val localDatasource = LocalDatasource(applicationContext)
        val factory = MovieViewModelFactory(localDatasource)

        movieViewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

        setContent {
            ApiAppTheme {
                AppNavigation(movieViewModel)
            }
        }
    }
}
