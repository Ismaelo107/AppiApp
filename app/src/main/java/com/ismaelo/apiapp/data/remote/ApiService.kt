package com.ismaelo.apiapp.data.remote

import com.ismaelo.apiapp.core.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing")
    suspend fun getAllMovies(
        @Query("api_key") apiKey: String
    ): Response<MovieResponse>

    @GET("movie/popular")
    suspend fun getPopular(
        @Query("api_key") apiKey: String
    ): Response<MovieResponse>

    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("api_key") apiKey: String
    ): Response<MovieResponse>

    @GET("movie/upcoming")
    suspend fun getUpComing(
        @Query("api_key") apiKey: String
    ): Response<MovieResponse>
}
