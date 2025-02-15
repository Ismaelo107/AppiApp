package com.ismaelo.apiapp.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ismaelo.apiapp.viewModel.MovieViewModel

@Composable
fun NowPlaying(movieViewModel: MovieViewModel, navController: NavHostController) {
    val movies by movieViewModel.nowPlayingMovies.collectAsState()
    val isLoading by movieViewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        movieViewModel.fetchNowPlayingMovies()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Now Playing Movies", style = MaterialTheme.typography.titleLarge)

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
