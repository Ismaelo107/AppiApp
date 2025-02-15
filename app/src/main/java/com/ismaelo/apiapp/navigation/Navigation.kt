package com.ismaelo.apiapp.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ismaelo.apiapp.ui.view.MainScreen
import com.ismaelo.apiapp.ui.view.MovieDetailScreen
import com.ismaelo.apiapp.ui.view.NowPlaying
import com.ismaelo.apiapp.ui.view.Popular
import com.ismaelo.apiapp.ui.view.TopRated
import com.ismaelo.apiapp.ui.view.Upcoming
import com.ismaelo.apiapp.viewModel.MovieViewModel

@Composable
fun AppNavigation(movieViewModel: MovieViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home",
    ) {
        composable("home") {
            MainScreen(movieViewModel, navController)
        }
        composable("popular") {
            Popular(movieViewModel, navController)
        }
        composable("now_playing") {
            NowPlaying(movieViewModel, navController)
        }
        composable("top_rated") {
            TopRated(movieViewModel, navController)
        }
        composable("up_coming") {
            Upcoming(movieViewModel, navController)
        }

        composable("movie_details/{movieId}") { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")
            MovieDetailScreen(movieId)
        }
    }

}
