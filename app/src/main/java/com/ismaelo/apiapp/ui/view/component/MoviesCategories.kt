package com.ismaelo.apiapp.ui.view.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ismaelo.apiapp.navigation.Destinations

@Composable
fun MovieCategories(navController: NavHostController) {
    val categories = listOf(
        "❤️ Favoritos" to Destinations.Favorite_route.route,
        "🔥 Populares" to Destinations.Popular.route,
        "🎞️ En Cartelera" to Destinations.NowPlaying.route,
        "🏆 Mejor Valoradas" to Destinations.TopRated.route,
        "🚀 Próximas" to Destinations.UpComing.route
    )

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(categories) { (label, route) ->
            Box(
                modifier = Modifier.border(
                    1.dp, Color.Cyan, shape = MaterialTheme.shapes.extraLarge
                )
            ) {
                Button(
                    onClick = { navController.navigate(route) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                ) {
                    Text(text = label, color = Color.Cyan)
                }
            }
        }
    }
}