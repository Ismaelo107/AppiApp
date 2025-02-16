package com.ismaelo.apiapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {

    @Insert
    suspend fun insert(movie: Movie)

    @Query("SELECT * FROM movies")
    suspend fun getAllMovies(): List<Movie>

    @Query("DELETE FROM movies WHERE id = :movieId")
    suspend fun delete(movieId: String)
}
