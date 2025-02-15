package com.ismaelo.apiapp.data.loca

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey val id: String,
    val title: String,
    val overview: String,
    val image: String,
    val popularity: String, // FLOAT??
    val rating: String // FLOAT??
)
