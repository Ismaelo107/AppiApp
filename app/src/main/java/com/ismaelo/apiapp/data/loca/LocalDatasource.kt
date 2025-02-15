package com.ismaelo.apiapp.data.loca


import android.content.Context
import android.util.Log
import com.ismaelo.apiapp.data.loca.AppDataBase
import com.ismaelo.apiapp.data.loca.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDatasource(applicationContext: Context) {
    private val db: AppDataBase = AppDataBase.getDatabase(applicationContext)

    // Obtener todas las películas desde la base de datos
    suspend fun getAll(): List<com.ismaelo.apiapp.data.loca.Movie> {
        return db.MovieDao().getAllMovies()  // Llamamos al DAO para obtener todas las películas
    }

    // Insertar una película en la base de datos
    suspend fun insert(movie: Movie) {
        withContext(Dispatchers.IO) {
            // Insertamos la película en la base de datos
            val result = db.MovieDao().insert(movie)
            Log.d("LOCALDS", "Resultado de la inserción: $result")
        }
    }

    // Eliminar una película de la base de datos
    suspend fun delete(movie: Movie) {
        withContext(Dispatchers.IO) {
            // Eliminamos la película de la base de datos
            db.MovieDao().delete(movieId = movie.id)
        }
    }
}
