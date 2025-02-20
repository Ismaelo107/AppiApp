package com.ismaelo.apiapp.ui.view

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CreditsScreen(modifier: Modifier = Modifier) {
    val opacity: Float by animateFloatAsState(
        targetValue = 1f, animationSpec = tween(durationMillis = 1000, easing = EaseInOut)
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(
                    Color(0x70000000), RoundedCornerShape(32.dp)
                )
                .padding(16.dp), contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),

                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Créditos de iMovies",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Yellow,


                    modifier = Modifier.padding(bottom = 32.dp)
                )


                Spacer(modifier = Modifier.height(24.dp))





                Text(
                    text = "Nombre App:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF00F3FF),

                    )
                Text(
                    text = "iMovies",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFF7400),

                    )
                Spacer(modifier = Modifier.height(16.dp))


                Text(
                    text = "Desarrollador:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF00F3FF),

                    )
                Text(
                    text = "Ismael Ouardane El Ghali",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFF7400),

                    )



                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Datos proporcionados por:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF00F3FF),

                    )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "The Movie Database (TMDB)",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFF7400),
                )


                Spacer(modifier = Modifier.height(16.dp))


                Text(
                    text = "Esta aplicación usa TMDB y la API de TMDB, pero no está avalada, certificada ni afiliada con TMDB.",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFFFF7400),

                    lineHeight = 24.sp // Añadir altura de línea para mejorar la legibilidad
                )


                Spacer(modifier = Modifier.height(16.dp))



                Text(
                    text = "Fecha de Creación: ",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF00F3FF),
                )

                Text(
                    text = "Febrero 2025 ",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF00F3FF),
                )



                Spacer(modifier = Modifier.height(16.dp))


                Text(
                    text = "Versión:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFFFF7400),

                    )
                Text(
                    text = "1.0.0",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFF7400),

                    )

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}
