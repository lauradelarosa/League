package com.delarosa.team.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.delarosa.team.data.database.dao.TeamDao
import com.delarosa.team.data.database.entities.Team

@Database(entities = [Team::class], version = 1,exportSchema = false)
abstract class PruebaDataBase : RoomDatabase() {

    companion object {
        fun build(context: Context) = Room.databaseBuilder(
            context,
            PruebaDataBase::class.java,
            "prueba-db"
        ).fallbackToDestructiveMigration().build()
    }

    abstract fun teamDao(): TeamDao
}