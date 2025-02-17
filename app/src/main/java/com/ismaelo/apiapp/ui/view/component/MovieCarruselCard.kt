package com.ismaelo.apiapp.ui.view.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ismaelo.apiapp.core.Constants
import com.ismaelo.apiapp.data.remote.MovieDTO

@Composable
fun MovieCarruselCard(movie: MovieDTO) {
    Card(modifier = Modifier.width(400.dp)) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(Constants.API_URL_IMG + movie.image),
                contentDescription = movie.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .aspectRatio(1f / 2f)
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}