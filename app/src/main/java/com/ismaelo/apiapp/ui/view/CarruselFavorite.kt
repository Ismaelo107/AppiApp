package com.ismaelo.apiapp.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.ismaelo.apiapp.core.Constants
import com.ismaelo.apiapp.data.local.Movie
import com.ismaelo.apiapp.navigation.Destinations
import com.ismaelo.apiapp.viewModel.MovieViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CaruselFavorite(movieViewModel: MovieViewModel = viewModel(), navController: NavHostController) {
    val favoriteMovies by movieViewModel.favoriteMovies.collectAsState()
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        movieViewModel.fetchFavoriteMovies()
    }

    LaunchedEffect(favoriteMovies) {
        if (favoriteMovies.isNotEmpty()) {
            while (true) {
                delay(5000)
                val nextIndex = (listState.firstVisibleItemIndex + 1) % favoriteMovies.size
                scope.launch { listState.animateScrollToItem(nextIndex) }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val categories = listOf(
            "Popular Movies" to Destinations.Popular.route,
            "Now Playing Movies" to Destinations.NowPlaying.route,
            "Top Rated Movies" to Destinations.TopRated.route,
            "Top UpComings" to Destinations.UpComing.route,
            "Ver mis favoritos" to Destinations.Favorite_route.route
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(categories) { (label, route) ->
                Button(onClick = { navController.navigate(route) }) {
                    Text(text = label)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Carrusel de películas favoritas
        if (favoriteMovies.isNotEmpty()) {
            Text(text = "Películas Favoritas", style = MaterialTheme.typography.titleMedium)
            LazyRow(
                state = listState, horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(favoriteMovies) { movie ->
                    FavoriteMovieCardCarrusel0(movie)
                }
            }
        }
    }
}

@Composable
fun FavoriteMovieCardCarrusel0(movie: Movie) {
    Card(

        modifier = Modifier.width(400.dp)
    ) {
        Column(
        ) {
            Image(
                painter = rememberAsyncImagePainter(Constants.API_URL_IMG + movie.image),
                contentDescription = movie.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .aspectRatio(1f / 2f)

            )

            Spacer(modifier = Modifier.height(8.dp))


        }
    }
}
