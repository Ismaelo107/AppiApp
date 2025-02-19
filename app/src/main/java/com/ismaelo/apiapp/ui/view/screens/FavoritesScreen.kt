package com.ismaelo.apiapp.ui.view.screens


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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.ismaelo.apiapp.data.local.Movie
import com.ismaelo.apiapp.viewModel.MovieViewModel

@Composable
fun FavoritesScreen(movieViewModel: MovieViewModel, navController: NavHostController) {
    val favoriteMovies by movieViewModel.favoriteMovies.collectAsState()

    LaunchedEffect(Unit) {
        movieViewModel.fetchFavoriteMovies()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = " ❤️ Favorite Movies",
            style = MaterialTheme.typography.titleLarge,
            color = Color.Cyan,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        if (favoriteMovies.isEmpty()) {
            Text("No favorites yet", style = MaterialTheme.typography.bodyMedium)
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize()
            ) {
                items(favoriteMovies.size) { index ->
                    FavoriteMovieCard(
                        movie = favoriteMovies[index],
                        navController = navController,
                        movieViewModel = movieViewModel
                    )
                }
            }
        }

    }
}

@Composable
fun FavoriteMovieCard(
    movie: Movie, navController: NavHostController, movieViewModel: MovieViewModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${movie.image}"),
                contentDescription = movie.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = movie.title,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Rating: ${movie.rating}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            IconButton(
                onClick = {
                    movieViewModel.deleteMovieFromLocal(movie)
                }, modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Remove Favorite",
                    tint = Color.Red
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = { navController.navigate("local_movie_details/${movie.id}") }) {
                Text(text = "See Details")
            }
        }

    }
}
