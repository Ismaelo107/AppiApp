package com.ismaelo.apiapp.ui.view.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ismaelo.apiapp.navigation.Destinations

@Composable
fun NoConnectionScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 35.dp)
            .padding(top = 200.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "No hay conexión a Internet",
                style = MaterialTheme.typography.headlineMedium.copy(color = Color.Red),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Por favor, verifica tu conexión.",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            Button(
                onClick = {
                    navController.navigate(Destinations.Home.route)
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .border(2.dp, Color.Yellow, RoundedCornerShape(100.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),

                ) {
                Text(
                    text = "Intentar de nuevo", color = Color(0xFFFF7400)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    navController.navigate(Destinations.Favorite_route.route)
                },

                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .border(2.dp, Color.Yellow, RoundedCornerShape(100.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            ) {
                Text(
                    text = "Ir a Favoritos", color = Color(0xFFFF7400)
                )
            }
        }
    }
}
