package com.ismaelo.apiapp.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ismaelo.apiapp.navigation.Destinations
import com.ismaelo.apiapp.ui.view.component.MovieCard
import com.ismaelo.apiapp.viewModel.MovieViewModel

@Composable
fun UpComing(movieViewModel: MovieViewModel, navController: NavHostController) {
    val movies by movieViewModel.popularMovies.collectAsState()
    val isLoading by movieViewModel.isLoading.collectAsState()
    val isConnected by movieViewModel.isConnected.collectAsState() // Observamos el estado de conexión

    // Si no hay conexión, redirigir a MainScreen
    LaunchedEffect(isConnected) {
        if (!isConnected) {
            navController.navigate("main_screen") // Asegúrate de que "main_screen" está en tu NavGraph
        }
    }

    // Cargar las películas populares solo una vez
    LaunchedEffect(Unit) {
        movieViewModel.fetchUpcomingMovies()
        movieViewModel.fetchFavoriteMovies()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Popular Movies", style = MaterialTheme.typography.titleLarge)

        when {
            isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            movies.isNotEmpty() -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize()
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
                Text("No movies available.", color = Color.Red)
                Button(
                    onClick = { navController.navigate(route = Destinations.Favorite_route.route) },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Ver mis favoritos")
                }
            }
        }
    }
}
