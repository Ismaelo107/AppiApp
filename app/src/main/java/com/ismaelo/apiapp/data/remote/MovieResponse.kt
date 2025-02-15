package com.ismaelo.apiapp.data.remote

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results") var results: List<MovieDTO>
)
