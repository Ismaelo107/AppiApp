package com.ismaelo.apiapp.navigation.navigationBar

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ismaelo.apiapp.navigation.Destinations

@Composable
fun DrawerContent(navController: NavHostController, closeDrawer: () -> Unit) {
    ModalDrawerSheet {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            DrawerHeader()

            DrawerSectionTitle("Home")
            DrawerItem(icon = Icons.Default.Home, label = "Home") {
                navController.navigate(Destinations.Home.route)
                closeDrawer()
            }

            DrawerSectionTitle("Favorites")
            DrawerItem(icon = Icons.Default.Favorite, label = "My Favorites") {
                navController.navigate(Destinations.Favorite_route.route)
                closeDrawer()
            }

            DrawerSectionTitle("Categories")
            DrawerItem(icon = Icons.Default.PlayArrow, label = "Now Playing Movies") {
                navController.navigate(Destinations.NowPlaying.route)
                closeDrawer()
            }
            DrawerItem(icon = Icons.Default.Star, label = "Popular Movies") {
                navController.navigate(Destinations.Popular.route)
                closeDrawer()
            }
            DrawerItem(icon = Icons.Default.ThumbUp, label = "Top Rated Movies") {
                navController.navigate(Destinations.TopRated.route)
                closeDrawer()
            }
            DrawerItem(icon = Icons.Default.KeyboardArrowUp, label = "Upcoming") {
                navController.navigate(Destinations.UpComing.route)
                closeDrawer()
            }

            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            DrawerItem(icon = Icons.Default.Info, label = "Autor y Créditos") {
                navController.navigate(Destinations.Credits.route)
                closeDrawer()
            }
        }
    }
}

@Composable
fun DrawerHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "iMovies",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Explora el mundo del cine, encuentra lo que necesitas. " +
                    "Lo más votado, lo más popular y guarda tus preferidas.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
        )
    }
}

@Composable
fun DrawerSectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
    )
}

@Composable
fun DrawerItem(icon: ImageVector, label: String, onClick: () -> Unit) {
    NavigationDrawerItem(
        label = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        selected = false,
        onClick = onClick,
        modifier = Modifier.padding(horizontal = 8.dp)
    )
}