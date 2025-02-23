package com.ismaelo.apiapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey val id: String,
    val title: String,
    val overview: String,
    val image: String,
    val popularity: String,
    val rating: String
)
