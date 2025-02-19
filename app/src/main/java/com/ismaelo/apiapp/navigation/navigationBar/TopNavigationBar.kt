package com.ismaelo.apiapp.navigation.navigationBar

import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavigationBar(
    onMenuClick: () -> Unit
) {
    TopAppBar(title = { Text(text = "Movies", color = Color.Gray) },
        navigationIcon = {
            IconButton(onClick = { onMenuClick() }) {
                Icon(
                    Icons.Filled.Menu, contentDescription = "Menu", tint = Color.Gray
                )
            }
        },
        modifier = Modifier.height(80.dp),
        colors = androidx.compose.material3.TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        actions = {

        })

}


