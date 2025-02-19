package com.ismaelo.apiapp.ui.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
import com.ismaelo.apiapp.ui.view.component.AnimatedGradientBackground
import com.ismaelo.apiapp.ui.view.component.MovieCard
import com.ismaelo.apiapp.viewModel.MovieViewModel

@Composable
fun MovieScreen(
    title: String,
    fetchMovies: () -> Unit,
    movieViewModel: MovieViewModel,
    navController: NavHostController
) {
    val movies by movieViewModel.popularMovies.collectAsState()
    val isLoading by movieViewModel.isLoading.collectAsState()


    LaunchedEffect(Unit) {
        fetchMovies()
        movieViewModel.fetchFavoriteMovies()
    }

    Box(modifier = Modifier.fillMaxSize()) {

        AnimatedGradientBackground()


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Color.Transparent)
        ) {
            Text(text = title, style = MaterialTheme.typography.titleLarge, color = Color.White)

            when {
                isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                movies.isNotEmpty() -> LazyVerticalGrid(
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

                else -> NoMoviesAvailableScreens(navController)
            }
        }
    }
}

@Composable
fun NoMoviesAvailableScreens(navController: NavHostController) {
    AnimatedGradientBackground() // Fondo animado o transparente

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(align = Alignment.CenterVertically)
            .padding(16.dp)
            .background(Color.Transparent), // Fondo transparente
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "No movies available.", color = Color.White, style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate(route = Destinations.Favorite_route.route) },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "See my favorites")
        }
    }
}

@Composable
fun NowPlaying(movieViewModel: MovieViewModel, navController: NavHostController) {
    MovieScreen(
        title = "Now Playing",
        fetchMovies = { movieViewModel.fetchNowPlayingMovies() },
        movieViewModel = movieViewModel,
        navController = navController
    )
}

@Composable
fun Popular(movieViewModel: MovieViewModel, navController: NavHostController) {
    MovieScreen(
        title = "Popular Movies",
        fetchMovies = { movieViewModel.fetchPopularMovies() },
        movieViewModel = movieViewModel,
        navController = navController
    )
}

@Composable
fun TopRated(movieViewModel: MovieViewModel, navController: NavHostController) {
    MovieScreen(
        title = "Top Rated Movies",
        fetchMovies = { movieViewModel.fetchTopRatedMovies() },
        movieViewModel = movieViewModel,
        navController = navController
    )
}

@Composable
fun UpComing(movieViewModel: MovieViewModel, navController: NavHostController) {
    MovieScreen(
        title = "Upcoming Movies",
        fetchMovies = { movieViewModel.fetchUpcomingMovies() },
        movieViewModel = movieViewModel,
        navController = navController
    )
}
