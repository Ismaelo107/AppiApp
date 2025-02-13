package com.ismaelo.apiapp.data.loca

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Comic::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun comicDao(): ComicDao

    companion object {
        @Volatile
        private var Instance: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            return Instance ?: synchronized(this) {
                Room
                    .databaseBuilder(context, AppDataBase::class.java, "comicsdb.sql")
                    .build()
                    .also { Instance = it }

            }
        }
    }
}