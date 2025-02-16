package com.ismaelo.apiapp.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ismaelo.apiapp.viewModel.MovieViewModel

@Composable
fun TopRated(movieViewModel: MovieViewModel, navController: NavHostController) {
    val movies by movieViewModel.topRatedMovies.collectAsState()
    val isLoading by movieViewModel.isLoading.collectAsState()

    // Obtener las películas más valoradas desde el ViewModel
    LaunchedEffect(Unit) {
        movieViewModel.fetchTopRatedMovies()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Top Rated Movies", style = MaterialTheme.typography.titleLarge)

        // Mostrar texto de carga si estamos esperando la respuesta
        if (isLoading) {
            Text("Loading...")
        } else {
            // Usamos LazyVerticalGrid para mostrar las películas en cuadrícula
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // Dos columnas
                modifier = Modifier.fillMaxSize()
            ) {
                items(movies.size) { index ->
                    MovieCard(movie = movies[index], navController = navController, movieViewModel = movieViewModel)
                }
            }
        }
    }
}
