package com.ismaelo.apiapp.ui.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.ismaelo.apiapp.navigation.Destinations
import com.ismaelo.apiapp.ui.view.component.MovieCarusel
import com.ismaelo.apiapp.viewModel.MovieViewModel

@Composable
fun MainScreen(movieViewModel: MovieViewModel = viewModel(), navController: NavHostController) {
    val popularMovies by movieViewModel.popularMovies.collectAsState()
    val nowPlayingMovies by movieViewModel.nowPlayingMovies.collectAsState()
    val topRatedMovies by movieViewModel.topRatedMovies.collectAsState()
    val upcomingMovies by movieViewModel.upcomingMovies.collectAsState()


    // Fetch data when screen is loaded
    LaunchedEffect(Unit) {
        movieViewModel.fetchPopularMovies()
        movieViewModel.fetchNowPlayingMovies()
        movieViewModel.fetchTopRatedMovies()
        movieViewModel.fetchUpcomingMovies()
    }

    LazyColumn {
        item {
            // Barra de categorías
            MovieCategories(navController = navController)
        }

        item {
            // Carrusel de películas populares
            MovieCarusel(
                title = "Películas Populares",
                movies = popularMovies,
                navController = navController
            )
        }
        item {
            // Carrusel de películas ahora en cartelera
            MovieCarusel(
                title = "Películas Ahora en Cartelera",
                movies = nowPlayingMovies,
                navController = navController
            )
        }
        item {
            // Carrusel de películas mejor valoradas
            MovieCarusel(
                title = "Películas Mejor Valoradas",
                movies = topRatedMovies,
                navController = navController
            )
        }
        item {
            // Carrusel de películas próximas
            MovieCarusel(
                title = "Próximas Películas", movies = upcomingMovies, navController = navController
            )
        }
    }
}


@Composable
fun MovieCategories(navController: NavHostController) {
    val categories = listOf(
        "Popular Movies" to Destinations.Popular.route,
        "Now Playing Movies" to Destinations.NowPlaying.route,
        "Top Rated Movies" to Destinations.TopRated.route,
        "Top UpComings" to Destinations.UpComing.route
    )

    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(categories) { (label, route) ->
            Button(onClick = { navController.navigate(route) }) {
                Text(text = label)
            }
        }
    }
}

