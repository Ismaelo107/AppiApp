package com.ismaelo.apiapp.data.local

class MovieRepository(
    private val localDatasource: LocalDatasource
) {

    suspend fun getLocalMovies(): List<Movie> {
        return localDatasource.getAll()
    }

    suspend fun getLocalMovieById(id: String): Movie? {
        return localDatasource.getById(id) // Nueva función para obtener una película por ID
    }

    suspend fun insertLocalMovie(movie: Movie) {
        localDatasource.insert(movie)
    }

    suspend fun deleteLocalMovie(movie: Movie) {
        localDatasource.delete(movie)
    }
}
