package com.ismaelo.apiapp.data.loca

import android.content.Context
import android.util.Log
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.stateIn


class LocalDatasource(applicationContext: Context) {
    private val db: AppDataBase = AppDataBase.getDatabase(applicationContext)

    @OptIn(DelicateCoroutinesApi::class)
    suspend fun getAll(): Flow<List<Comic>> {
        return db.comicDao().getAll().stateIn(GlobalScope)
    }

    fun insert(comic: Comic) {
        val result = db.comicDao().insert(comic)
        Log.d("LOCALDS", "Resultado: $result")
    }

    fun delete(comic: Comic) {
        db.comicDao().delete(comic)
    }

}