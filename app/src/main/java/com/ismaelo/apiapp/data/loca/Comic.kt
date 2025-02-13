package com.ismaelo.apiapp.data.loca

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


/**
 * Definimos los atributos de nuestra entidad, anotando mediante Room.
 *
 * Para simplificar y para futuras proyecciones, recogemos todos los datos que nos aporta la API
 * en este caso, dado que no son demasiados.
 *
 * Estos campos provienen de la API que leemos en remote. Así, el comic número 1 está accesible con
 * la siguiente URI: https://xkcd.com/1/info.0.json
 */

@Entity(tableName = "comics", indices = [Index(value = ["num"], unique = true)])

data class Comic(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val alt: String,
    val day: String,
    val img: String,
    val link: String,
    val month: String,
    val news: String,
    val num: Int,
    val safe_title: String,
    val title: String,
    val transcript: String,
    val year: String
)