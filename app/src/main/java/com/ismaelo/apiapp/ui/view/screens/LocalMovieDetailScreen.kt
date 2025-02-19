package com.ismaelo.apiapp.ui.view.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ismaelo.apiapp.ui.util.ScreenState
import com.ismaelo.apiapp.viewModel.MovieViewModel
@Composable
fun LocalMovieDetailScreen(movieId: String, viewModel: MovieViewModel) {
    // Obtener los detalles de la película desde el ViewModel
    val movieDetail by viewModel.movieDetailFromLocal.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    // Llamada para obtener la película al cargar el composable
    LaunchedEffect(movieId) {
        viewModel.fetchMovieFromLocalById(movieId)
    }

    // Mostrar el estado de carga mientras obtenemos los datos
    if (isLoading) {
        // Mostrar indicador de carga
        Text(text = "Cargando...", style = MaterialTheme.typography.bodyLarge)
    } else {
        // Mostrar errores si existen
        if (uiState is ScreenState.Error) {
            Text(
                text = (uiState as ScreenState.Error).message,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyLarge
            )
        } else {
            // Mostrar los detalles de la película cuando se obtiene correctamente
            movieDetail?.let { movie ->
                Column(modifier = Modifier.padding(16.dp)) {
                    // Título de la película
                    Text(text = movie.title, style = MaterialTheme.typography.headlineMedium)

                    Spacer(modifier = Modifier.height(16.dp))

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


                    // Descripción de la película
                    Text(
                        text = movie.overview ?: "Descripción no disponible.",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    // Rating de la película
                    Text(text = "Rating: ${movie.rating}", style = MaterialTheme.typography.bodyMedium)

                    Spacer(modifier = Modifier.height(8.dp))

                    // Popularidad de la película
                    Text(text = "Popularidad: ${movie.popularity}", style = MaterialTheme.typography.bodyMedium)

                    Spacer(modifier = Modifier.height(16.dp))

                    // Agregar otros detalles si es necesario
                    // Por ejemplo, una fila con el año de estreno, género, etc.
                }
            }

            // Si la película no se encuentra, mostrar mensaje de error
            if (movieDetail == null) {
                Text(
                    text = "No se encontró la película.",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
