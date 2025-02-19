package com.ismaelo.apiapp.navigation.navigationBar

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ismaelo.apiapp.navigation.Destinations

@Composable
fun DrawerContent(navController: NavHostController) {
    ModalDrawerSheet {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(vertical = 16.dp)
        ) {
            // Encabezado del Drawer
            DrawerHeader()

            // Sección de Inicio
            DrawerSectionTitle("Home")
            DrawerItem(
                icon = Icons.Default.Home,
                label = "Home",
                onClick = { navController.navigate(Destinations.Home.route) }
            )

            // Sección de Favoritos
            DrawerSectionTitle("Favorites")
            DrawerItem(
                icon = Icons.Default.Favorite,
                label = "My Favorites",
                onClick = { navController.navigate(Destinations.Favorite_route.route) }
            )

            // Sección de Categorías de Películas
            DrawerSectionTitle("Categories")
            DrawerItem(
                icon = Icons.Default.PlayArrow,
                label = "Now Play Movies",
                onClick = { navController.navigate(Destinations.NowPlaying.route) }
            )
            DrawerItem(
                icon = Icons.Default.Star,
                label = "Popular Movies",
                onClick = { navController.navigate(Destinations.Popular.route) }
            )
            DrawerItem(
                icon = Icons.Default.ThumbUp,
                label = "Top Rated Movies",
                onClick = { navController.navigate(Destinations.TopRated.route) }
            )
            DrawerItem(
                icon = Icons.Default.KeyboardArrowUp,
                label = "UpComing",
                onClick = { navController.navigate(Destinations.UpComing.route) }
            )

            // Separador
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            // Sección de Créditos
            DrawerItem(
                icon = Icons.Default.Info,
                label = "Autor y Créditos",
                onClick = { navController.navigate(Destinations.Credits.route) }
            )
        }
    }
}

@Composable
fun DrawerHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "iMovies",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Explora el mundo del cine, encuentra lo que necesitas. Que es lo más votado," +
                    " lo más popular y guarda tus preferidas para que no se te olvide.",
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