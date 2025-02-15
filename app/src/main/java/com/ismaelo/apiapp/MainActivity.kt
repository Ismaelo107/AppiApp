package com.ismaelo.apiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ismaelo.apiapp.ui.theme.ApiAppTheme
import com.ismaelo.apiapp.viewModel.MovieViewModel

class MainActivity : ComponentActivity() {
    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApiAppTheme {
                MainScreen(movieViewModel)
            }
        }
    }
}

@Composable
fun MainScreen(movieViewModel: MovieViewModel) {
    val movies by movieViewModel.popularMovies.collectAsState()
    val isLoading by movieViewModel.isLoading.collectAsState()

    fun fetchMovies() {
        movieViewModel.fetchPopularMovies()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { fetchMovies() }) {
            Text(text = "Get Popular Movies")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            Text("Loading...")
        } else if (movies.isEmpty()) {
            Text("No Movies Found")
        } else {
            movies.forEach { movie ->
                Text(text = movie)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ApiAppTheme {
        MainScreen(movieViewModel = MovieViewModel())
    }
}
