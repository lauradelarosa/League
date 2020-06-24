package com.delarosa.teamdetail.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.delarosa.teamdetail.data.database.dao.EventDao
import com.delarosa.teamdetail.data.database.entities.Event

@Database(entities = [Event::class], version = 1,exportSchema = false)
abstract class PruebaDataBase : RoomDatabase() {

    companion object {
        fun build(context: Context) = Room.databaseBuilder(
            context,
            PruebaDataBase::class.java,
            "prueba-db"
        ).fallbackToDestructiveMigration().build()
    }

    abstract fun eventDao(): EventDao
}