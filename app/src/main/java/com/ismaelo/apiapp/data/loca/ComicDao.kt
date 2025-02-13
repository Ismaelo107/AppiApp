package com.ismaelo.apiapp.data.loca

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface ComicDao {

    @Query("SELECT * FROM comics")
    fun getAll(): Flow<List<Comic>>

    @Query("SELECT * FROM comics WHERE num = (SELECT MAX(num) FROM comics)")
    fun getLast(): Comic

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(comic: Comic): Long

    @Delete
    fun delete(comic: Comic)
}

