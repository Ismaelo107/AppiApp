package com.ismaelo.apiapp.data.loca

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies") // Cambié "comics" a "movies"
    fun getAll(): Flow<List<Movie>> // Aquí cambiaste a la entidad Movie


    @Insert(onConflict = OnConflictStrategy.REPLACE) // Inserta una película
    fun insert(movie: Movie): Long

    @Delete // Elimina una película
    fun delete(movie: Movie)
}
