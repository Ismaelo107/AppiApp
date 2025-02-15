package com.ismaelo.apiapp.data.remote

import retrofit2.Response

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

class EmptyResponseBodyException(message: String) : Exception(message)
class NetworkException(message: String) : Exception(message)

class RemoteDatasource(private val apiService: ApiService) {

    suspend fun getAllMovies(apiKey: String): Result<MovieResponse> {
        return try {
            val response = apiService.getAllMovies(apiKey)
            handleResponse(response)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun getPopularMovies(apiKey: String): Result<MovieResponse> {
        return try {
            val response = apiService.getPopular(apiKey)
            handleResponse(response)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun getTopRatedMovies(apiKey: String): Result<MovieResponse> {
        return try {
            val response = apiService.getTopRated(apiKey)
            handleResponse(response)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun getUpcomingMovies(apiKey: String): Result<MovieResponse> {
        return try {
            val response = apiService.getUpComing(apiKey)
            handleResponse(response)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    private fun handleResponse(response: Response<MovieResponse>): Result<MovieResponse> {
        return if (response.isSuccessful) {
            response.body()?.let {
                Result.Success(it)
            } ?: Result.Error(EmptyResponseBodyException("Empty response body"))
        } else {
            Result.Error(NetworkException("Error: ${response.message()} (${response.code()})"))
        }
    }
}
