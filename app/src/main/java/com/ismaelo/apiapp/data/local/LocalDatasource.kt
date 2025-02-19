package com.ismaelo.apiapp.data.local

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.Query

class LocalDatasource(applicationContext: Context) {
    private val db: AppDataBase = AppDataBase.getDatabase(applicationContext)

    suspend fun getAll(): List<Movie> {
        return db.MovieDao().getAllMovies()
    }

    suspend fun getById(id: String): Movie?{
        return db.MovieDao().getMovieById(id)
    }


    suspend fun insert(movie: Movie) {
        withContext(Dispatchers.IO) {
            db.MovieDao().insert(movie)
        }
    }

    suspend fun delete(movie: Movie) {
        withContext(Dispatchers.IO) {
            db.MovieDao().delete(movieId = movie.id)
        }
    }
}
