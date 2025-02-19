package com.ismaelo.apiapp.navigation.navigationBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavigationBar(
    onMenuClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "iMovies", // Cambiar el título a "iMovies"
                color = Color.Cyan, // Texto en cian
                fontSize = 24.sp, // Texto más grande
                fontWeight = FontWeight.Bold // Texto más grueso
            )
        },
        navigationIcon = {
            IconButton(onClick = { onMenuClick() }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu",
                    tint = Color.Cyan // Icono en cian
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent // Fondo transparente
        ),
        actions = {
            // Aquí puedes agregar más acciones si es necesario
        }
    )
}