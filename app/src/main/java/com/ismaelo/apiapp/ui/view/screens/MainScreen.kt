package com.ismaelo.apiapp.ui.view.screens

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.ismaelo.apiapp.data.remote.MovieDTO
import com.ismaelo.apiapp.ui.view.component.CaruselFavorite
import com.ismaelo.apiapp.ui.view.component.MovieCarusel
import com.ismaelo.apiapp.ui.view.component.MovieCategories
import com.ismaelo.apiapp.viewModel.MovieViewModel

@Composable
fun MainScreen(
    movieViewModel: MovieViewModel = viewModel(), navController: NavHostController
) {
    val popularMovies by movieViewModel.popularMovies.collectAsState()
    val nowPlayingMovies by movieViewModel.nowPlayingMovies.collectAsState()
    val topRatedMovies by movieViewModel.topRatedMovies.collectAsState()
    val upcomingMovies by movieViewModel.upcomingMovies.collectAsState()
    val isConnected = checkInternetConnection(LocalContext.current)

    LaunchedEffect(Unit) {
        if (isConnected) {
            movieViewModel.fetchPopularMovies()
            movieViewModel.fetchNowPlayingMovies()
            movieViewModel.fetchTopRatedMovies()
            movieViewModel.fetchUpcomingMovies()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(1.dp)
            .background(Color.Transparent)
    ) {
        if (!isConnected) {
            NoConnectionScreen(navController)
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                item { MovieCategories(navController) }

                //item { CaruselFavorite(movieViewModel, navController) }

                item {
                    Text(
                        text = "🎬 Películas Populares",
                        color = Color.Cyan,
                        modifier = Modifier.padding(top = 16.dp, bottom = 4.dp) // Reducción del padding inferior
                    )
                    MovieCaruselSection(
                        movies = popularMovies, navController = navController
                    )
                }
                item {
                    Text(
                        text = "📽️ Ahora en Cartelera",
                        color = Color.Cyan,
                        modifier = Modifier.padding(top = 16.dp, bottom = 4.dp) // Reducción del padding inferior
                    )
                    MovieCaruselSection(
                        movies = nowPlayingMovies, navController = navController
                    )
                }
                item {
                    Text(
                        text = "⭐ Mejor Valoradas",
                        color = Color.Cyan,
                        modifier = Modifier.padding(top = 16.dp, bottom = 4.dp) // Reducción del padding inferior
                    )
                    MovieCaruselSection(
                        movies = topRatedMovies, navController = navController
                    )
                }
                item {
                    Text(
                        text = "🎥 Próximos Estrenos",
                        color = Color.Cyan,
                        modifier = Modifier.padding(top = 16.dp, bottom = 4.dp) // Reducción del padding inferior
                    )
                    MovieCaruselSection(
                        movies = upcomingMovies, navController = navController
                    )
                }
            }
        }
    }
}
fun checkInternetConnection(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkCapabilities = connectivityManager.activeNetwork?.let {
        connectivityManager.getNetworkCapabilities(it)
    }
    return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
}

@Composable
fun MovieCaruselSection(movies: List<MovieDTO>, navController: NavHostController
) {
    MovieCarusel(navController = navController, movies = movies)
}

