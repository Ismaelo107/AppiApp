package com.ismaelo.apiapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {

    @GET("info.0.json")
    suspend fun getLastComic(): ComicDTO

    //Ojo, Path: https://guides.codepath.com/android/consuming-apis-with-retrofit
    @GET("{num}/info.0.json")
    suspend fun getComic(@Path("num") num: Int): ComicDTO

}