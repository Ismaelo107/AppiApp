package com.ismaelo.apiapp.data.local

import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDatasource(applicationContext: Context) {
    private val db: AppDataBase = AppDataBase.getDatabase(applicationContext)

    suspend fun getAll(): List<Movie> {
        return db.MovieDao().getAllMovies() // Retorna todas las películas almacenadas
    }

    suspend fun insert(movie: Movie) {
        // Ejecuta la inserción en un hilo de fondo
        withContext(Dispatchers.IO) {
            val result = db.MovieDao().insert(movie)
            Log.d("LOCALDS", "Resultado de la inserción: $result")
        }
    }

    suspend fun delete(movie: Movie) {
        withContext(Dispatchers.IO) {
            db.MovieDao().delete(movieId = movie.id)
        }
    }
}
