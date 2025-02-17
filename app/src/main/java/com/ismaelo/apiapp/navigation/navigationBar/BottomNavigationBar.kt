package com.ismaelo.apiapp.navigation.navigationBar


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.ismaelo.apiapp.navigation.Destinations


@Composable
fun BottomNavigationBar(navController: NavHostController) {


    BottomAppBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround
        ) {

            IconButton(onClick = { navController.navigate(Destinations.Home.route) }) {
                Icon(Icons.Filled.Home, contentDescription = "Home")
            }
            IconButton(onClick = { navController.navigate(Destinations.Popular.route) }) {
                Icon(Icons.Filled.Star, contentDescription = "Categories")
            }
            IconButton(onClick = { navController.navigate(Destinations.Favorite_route.route) }) {
                Icon(Icons.Filled.Favorite, contentDescription = "Favorites")
            }
            IconButton(onClick = { navController.navigate(Destinations.Favorite_route.route) }) {
                Icon(Icons.Filled.Person, contentDescription = "Fuentes")
            }
        }
    }


}
