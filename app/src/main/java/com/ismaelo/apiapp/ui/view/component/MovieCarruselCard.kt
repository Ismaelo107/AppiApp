package com.ismaelo.apiapp.ui.view.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.ismaelo.apiapp.core.Constants
import com.ismaelo.apiapp.data.remote.MovieDTO

@Composable
fun MovieCarruselCard(movie: MovieDTO, navController: NavHostController) {
    Card(
        modifier = Modifier
            .width(380.dp)
            .border(
                2.dp, Color.Black, shape = MaterialTheme.shapes.medium
            )
    ) {
        Column {
            Image(painter = rememberAsyncImagePainter(Constants.API_URL_IMG + movie.image),
                contentDescription = movie.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .aspectRatio(1f / 2f)
                    .clickable { navController.navigate("movie_details/${movie.id}") })

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}