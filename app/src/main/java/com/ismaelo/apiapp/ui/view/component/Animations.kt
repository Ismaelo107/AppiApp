package com.ismaelo.apiapp.ui.view.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun AnimatedGradientBackground() {
    val infiniteTransition = rememberInfiniteTransition()
    val offsetX by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 10000f, animationSpec = infiniteRepeatable(
            animation = tween(10000, easing = LinearEasing), repeatMode = RepeatMode.Reverse
        )
    )

    Canvas(modifier = Modifier.fillMaxSize()) {
        val gradient = Brush.linearGradient(
            colors = listOf(
                Color.Black,
                Color.DarkGray,
                Color.Cyan,

                ), start = Offset(offsetX, 0f), end = Offset(offsetX + 500f, 1000f)
        )
        drawRect(brush = gradient, size = size)
    }
}