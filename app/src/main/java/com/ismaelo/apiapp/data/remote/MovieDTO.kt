package com.ismaelo.apiapp.data.remote

import com.google.gson.annotations.SerializedName
import com.ismaelo.apiapp.data.local.Movie


data class MovieDTO(
    @SerializedName("id")
    val id: String,
    @SerializedName("original_title")
    val title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val image: String,
    @SerializedName("popularity")
    val popularity: String,
    @SerializedName("vote_average")
    val rating: String,
    /* @SerializedName("release_date")
     val date: String*/
)

fun MovieDTO.toLocalMovie() =
    Movie(
        id = id,
        title = title,
        overview = overview,
        image = image,
        popularity = popularity,
        rating = rating
    )

