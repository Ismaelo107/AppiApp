package com.ismaelo.apiapp.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.ismaelo.apiapp.data.remote.MovieDTO
import com.ismaelo.apiapp.data.remote.toLocalMovie
import com.ismaelo.apiapp.navigation.Destinations
import com.ismaelo.apiapp.ui.view.component.MovieCard
import com.ismaelo.apiapp.viewModel.MovieViewModel
@Composable
fun Popular(movieViewModel: MovieViewModel, navController: NavHostController) {
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
        movieViewModel.fetchPopularMovies()
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
