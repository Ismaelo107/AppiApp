package com.ismaelo.apiapp.ui.view.component

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ismaelo.apiapp.data.remote.MovieDTO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MovieCarusel(

    movies: List<MovieDTO>,
    navController: NavHostController,

) {
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(movies) {
        if (movies.isNotEmpty()) {
            while (true) {
                delay(3000)
                if (!listState.isScrollInProgress) {
                    val nextIndex = (listState.firstVisibleItemIndex + 1) % movies.size
                    scope.launch { listState.animateScrollToItem(nextIndex) }
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Transparent)
    ) {
        Spacer(modifier = Modifier.height(24.dp))



        if (movies.isNotEmpty()) {
            LazyRow(
                state = listState, horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(movies) { movie ->
                    MovieCarruselCard(navController = navController, movie = movie)
                }
            }
        } else {
            Text(
                text = "No hay películas disponibles",
                color = Color.Gray,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
