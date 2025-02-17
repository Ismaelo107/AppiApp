package com.ismaelo.apiapp.navigation.NavigationDrawer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ismaelo.apiapp.navigation.Destinations


@Composable
fun DrawerContent(navController: NavHostController) {
    ModalDrawerSheet {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            NavigationDrawerItem(label = {
                Text(
                    "Home",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleLarge
                )
            },
                selected = false,
                onClick = { navController.navigate(Destinations.Favorite_route.route) })


            HorizontalDivider()
            Spacer(Modifier.height(12.dp))
            Text(
                "Drawer Title",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleLarge
            )
            HorizontalDivider()

            Text(
                "Favorite Movies",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleMedium
            )

            NavigationDrawerItem(label = { Text("Favorite") },
                selected = false,
                onClick = { navController.navigate(Destinations.Favorite_route.route) })

            HorizontalDivider()

            Text(
                "Movies Categories",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleMedium
            )

            NavigationDrawerItem(label = { Text("Play Now Movies") },
                selected = false,
                onClick = { navController.navigate(Destinations.NowPlaying.route) })

            NavigationDrawerItem(label = { Text("Popular Movies") },
                selected = false,
                onClick = { navController.navigate(Destinations.Popular.route) })

            NavigationDrawerItem(label = { Text("Top Rated Movies") },
                selected = false,
                onClick = { navController.navigate(Destinations.TopRated.route) })

            NavigationDrawerItem(label = { Text("Upcoming Movies") },
                selected = false,
                onClick = { navController.navigate(Destinations.UpComing.route) })


            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))


            NavigationDrawerItem(label = { Text("Author & Credits") },
                selected = false,
                onClick = { navController.navigate(Destinations.Credits.route) })

        }
    }
}