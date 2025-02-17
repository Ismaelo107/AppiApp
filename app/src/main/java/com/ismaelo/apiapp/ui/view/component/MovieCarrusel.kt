package com.ismaelo.apiapp.ui.view.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ismaelo.apiapp.data.remote.MovieDTO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MovieCarusel(title: String, movies: List<MovieDTO>, navController: NavHostController) {
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()


    // Auto scroll en el carrusel cada 5 segundos
    LaunchedEffect(movies) {
        if (movies.isNotEmpty()) {
            while (true) {
                delay(3000)
                val nextIndex = (listState.firstVisibleItemIndex + 1) % movies.size
                scope.launch { listState.animateScrollToItem(nextIndex) }
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = title, style = MaterialTheme.typography.titleMedium)

        if (movies.isNotEmpty()) {
            LazyRow(
                state = listState, horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(movies) { movie ->
                    MovieCarruselCard(movie)
                }
            }
        }
    }
}