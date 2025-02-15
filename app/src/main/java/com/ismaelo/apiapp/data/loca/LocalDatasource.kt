package com.ismaelo.apiapp.data.loca

import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class LocalDatasource(applicationContext: Context) {
    private val db: AppDataBase = AppDataBase.getDatabase(applicationContext)

    suspend fun getAll(): Flow<List<Movie>> {
        return db.comicDao().getAll() // Retorna el Flow directamente sin necesidad de usar stateIn
    }

    suspend fun insert(movie: Movie) {
        // Ejecuta la inserción en un hilo de fondo con withContext para asegurar que se ejecute correctamente.
        withContext(Dispatchers.IO) {
            val result = db.comicDao().insert(movie)
            Log.d("LOCALDS", "Resultado: $result")
        }
    }

    suspend fun delete(movie: Movie) {
        withContext(Dispatchers.IO) {
            db.comicDao().delete(movie)
        }
    }
}
