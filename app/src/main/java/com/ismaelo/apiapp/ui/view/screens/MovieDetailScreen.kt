package com.ismaelo.apiapp.ui.view.screens

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MovieDetailScreen(movieId: String?) {
    Text(text = "Movie Details for $movieId")

}

@Preview(showBackground = true)
@Composable
fun MovieDetailScreenPreview() {
    MovieDetailScreen(movieId = "12345")
}
