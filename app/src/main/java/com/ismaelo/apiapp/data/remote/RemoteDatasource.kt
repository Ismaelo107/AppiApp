package com.ismaelo.apiapp.data.remote

class RemoteDatasource(private val apiService: ApiService) {

    suspend fun getLastComic() = apiService.getLastComic()

    suspend fun getComic(number: Int) = apiService.getComic(number)

}