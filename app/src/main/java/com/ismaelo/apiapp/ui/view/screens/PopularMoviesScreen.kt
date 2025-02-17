package com.ismaelo.apiapp.ui.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ismaelo.apiapp.navigation.Destinations
import com.ismaelo.apiapp.ui.view.component.MovieCard
import com.ismaelo.apiapp.viewModel.MovieViewModel

@Composable
fun Popular(movieViewModel: MovieViewModel, navController: NavHostController) {
    // Observando los estados
    val movies by movieViewModel.popularMovies.collectAsState()
    val isLoading by movieViewModel.isLoading.collectAsState()
    val isConnected by movieViewModel.isConnected.collectAsState()

    // Si no hay conexión, se navega a la pantalla principal
    LaunchedEffect(isConnected) {
        if (!isConnected) {
            navController.navigate("main_screen")
        }
    }

    // Obtener las películas cuando se entra a la pantalla
    LaunchedEffect(Unit) {
        movieViewModel.fetchPopularMovies()
        movieViewModel.fetchFavoriteMovies()
    }

    // UI principal
    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Título de la sección
        Text(
            text = "Popular Movies",
            style = MaterialTheme.typography.titleLarge.copy(color = Color.White),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Contenido basado en el estado de carga y datos
        when {
            isLoading -> {
                // Indicador de carga
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            movies.isNotEmpty() -> {
                // Mostrar las películas
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 150.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(movies.size) { index ->
                        MovieCard(
                            movie = movies[index],
                            navController = navController,
                            movieViewModel = movieViewModel
                        )
                    }
                }
            }

            else -> {
                // Si no hay películas disponibles
                NoMoviesAvailableScreen(navController)
            }
        }
    }
}

@Composable
fun NoMoviesAvailableScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(align = Alignment.CenterVertically)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("No movies available.", color = Color.Red, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate(route = Destinations.Favorite_route.route) },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "See my favorites")
        }
    }
}
