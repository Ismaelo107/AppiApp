package com.ismaelo.apiapp.ui.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ismaelo.apiapp.ui.util.ScreenState
import com.ismaelo.apiapp.viewModel.MovieViewModel
@Composable
fun MovieDetailScreen(movieId: String, viewModel: MovieViewModel) {
    val movieDetail by viewModel.movieDetail.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(movieId) {
        // Intentamos obtener los detalles de la película desde la API
        viewModel.fetchMovieById(movieId)

        // Si no se puede obtener desde la API (error o no hay conexión), obtenemos desde la base de datos local
        if (uiState is ScreenState.Error) {
            viewModel.fetchMovieFromLocalById(movieId)
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.padding(16.dp)) {

            when {
                isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))

                // Si hay un error, mostramos el mensaje de error
                uiState is ScreenState.Error -> {
                    Text(
                        text = "Error al cargar detalles de la película.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }

                // Si se obtuvo el detalle de la película, mostramos los datos
                movieDetail != null -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = movieDetail!!.title,
                            style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.onBackground),
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )

                        Image(
                            painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${movieDetail!!.image}"),
                            contentDescription = "Carátula de la película",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .border(
                                    2.dp,
                                    MaterialTheme.colorScheme.onSurface,
                                    RoundedCornerShape(16.dp)
                                ),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Descripción: ${movieDetail!!.overview}",
                            style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                            Text(
                                text = "Popularidad: ${movieDetail!!.popularity}",
                                style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Rating: ${movieDetail!!.rating}",
                                style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                            )
                        }
                    }
                }
            }
        }
    }
}
