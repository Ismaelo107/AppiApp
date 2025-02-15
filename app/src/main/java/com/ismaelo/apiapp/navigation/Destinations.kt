package com.ismaelo.apiapp.navigation

sealed class Destinations(val route: String) {
    object Home : Destinations(route = "home")
    object Popular : Destinations(route = "popular")
    object NowPlaying : Destinations("now_playing")
    object TopRated : Destinations("top_rated")
    object UpComing : Destinations("up_coming")
    object MovieDetails : Destinations("movie_details/{movieId}")
}