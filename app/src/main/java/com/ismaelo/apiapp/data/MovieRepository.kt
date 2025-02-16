package com.ismaelo.apiapp.data

import com.ismaelo.apiapp.data.local.LocalDatasource
import com.ismaelo.apiapp.data.local.Movie

class MovieRepository(
    private val localDatasource: LocalDatasource
) {

    suspend fun getLocalMovies(): List<Movie> {
        return localDatasource.getAll() // Devuelve los favoritos guardados en la base de datos
    }

    suspend fun insertLocalMovie(movie: Movie) {
        localDatasource.insert(movie)
    }

    suspend fun deleteLocalMovie(movie: Movie) {
        localDatasource.delete(movie)
    }
}
