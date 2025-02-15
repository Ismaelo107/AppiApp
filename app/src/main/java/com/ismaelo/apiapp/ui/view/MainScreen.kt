package com.ismaelo.apiapp.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ismaelo.apiapp.navigation.Destinations
import com.ismaelo.apiapp.viewModel.MovieViewModel

@Composable
fun MainScreen(movieViewModel: MovieViewModel, navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Movie Categories", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("popular") }) {
            Text(text = "Popular Movies")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { navController.navigate("now_playing") }) {
            Text(text = "Now Playing Movies")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { navController.navigate("top_rated") }) {
            Text(text = "Top Rated Movies")
        }
        Button(onClick = { navController.navigate(route = Destinations.UpComing.route) }) {
            Text(text = "Top UpComings")
        }
    }
}
