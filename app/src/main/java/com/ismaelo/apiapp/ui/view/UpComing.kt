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
fun Upcoming(movieViewModel: MovieViewModel, navController: NavHostController) {
    val movies by movieViewModel.upcomingMovies.collectAsState()
    val isLoading by movieViewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        movieViewModel.fetchUpcomingMovies()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Upcoming Movies", style = MaterialTheme.typography.titleLarge)

        if (isLoading) {
            Text("Loading...")
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize()
            ) {
                items(movies.size) { index ->
                    MovieCard(movie = movies[index], navController = navController)
                }
            }
        }
    }
}
