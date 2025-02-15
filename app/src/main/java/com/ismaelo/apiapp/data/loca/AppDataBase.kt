package com.ismaelo.apiapp.data.loca

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun comicDao(): MovieDao

    companion object {
        @Volatile
        private var Instance: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            return Instance ?: synchronized(this) {
                Room
                    .databaseBuilder(context, AppDataBase::class.java, "moviedtb.sql")
                    .build()
                    .also { Instance = it }

            }
        }
    }
}