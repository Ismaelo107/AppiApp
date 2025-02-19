package com.ismaelo.apiapp.ui.view.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.ismaelo.apiapp.data.remote.MovieDTO
import com.ismaelo.apiapp.data.remote.toLocalMovie
import com.ismaelo.apiapp.viewModel.MovieViewModel

@Composable
fun MovieCard(movie: MovieDTO, navController: NavHostController, movieViewModel: MovieViewModel) {
    val favorites by movieViewModel.favoriteMovies.collectAsState()
    val isFavorite = favorites.any { it.id == movie.id }

    val animatedElevation by animateDpAsState(
        targetValue = if (isFavorite) 6.dp else 2.dp, // Elevación más sutil
        animationSpec = tween(durationMillis = 500)
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(animatedElevation, shape = RoundedCornerShape(20.dp), clip = true)
            .clickable { navController.navigate("movie_details/${movie.id}") },
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${movie.image}"),
                contentDescription = movie.title,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.BottomStart)
                    .background(Color.Black.copy(alpha = 0.3f), shape = RoundedCornerShape(12.dp))
            ) {
                Row(
                    modifier = Modifier
                        .padding(7.dp)
                        .align(Alignment.BottomStart),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "⭐ ${movie.rating}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )

                    IconButton(
                        onClick = {
                            if (isFavorite) {
                                movieViewModel.deleteMovieFromLocal(movie.toLocalMovie())
                            } else {
                                movieViewModel.saveMovieToLocal(movie)
                            }
                        }, modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favorite",
                            tint = if (isFavorite) Color.Red else Color.Gray,
                            modifier = Modifier.animateContentSize()
                        )
                    }
                }
            }
        }
    }
}
