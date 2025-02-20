package com.ismaelo.apiapp.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ismaelo.apiapp.ui.view.CreditsScreen
import com.ismaelo.apiapp.ui.view.screens.FavoritesScreen
import com.ismaelo.apiapp.ui.view.screens.LocalMovieDetailScreen
import com.ismaelo.apiapp.ui.view.screens.MainScreen
import com.ismaelo.apiapp.ui.view.screens.MovieDetailScreen
import com.ismaelo.apiapp.ui.view.screens.NowPlaying
import com.ismaelo.apiapp.ui.view.screens.Popular
import com.ismaelo.apiapp.ui.view.screens.TopRated
import com.ismaelo.apiapp.ui.view.screens.UpComing
import com.ismaelo.apiapp.viewModel.MovieViewModel

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier, movieViewModel: MovieViewModel, navController: NavHostController
) {


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
            UpComing(movieViewModel, navController)
        }
        composable("favorite_Screen") {
            FavoritesScreen(movieViewModel, navController)
        }
        composable("creditos_screen") {
            CreditsScreen(modifier)
        }

        composable("movie_details/{movieId}") { backStackEntry ->
            val movieId: String = backStackEntry.arguments?.getString("movieId").toString()
            MovieDetailScreen(movieId, movieViewModel, navController)
        }
        composable("local_movie_details/{movieId}") { backStackEntry ->
            val movieId: String = backStackEntry.arguments?.getString("movieId").toString()
            LocalMovieDetailScreen(movieId, movieViewModel, navController)
        }
    }

}
