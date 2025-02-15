package com.ismaelo.apiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.ismaelo.apiapp.navigation.AppNavigation
import com.ismaelo.apiapp.ui.theme.ApiAppTheme
import com.ismaelo.apiapp.viewModel.MovieViewModel

class MainActivity : ComponentActivity() {
    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApiAppTheme {
                AppNavigation(movieViewModel)
            }
        }
    }
}


