package com.delarosa.league.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.delarosa.league.data.database.dao.LeagueDao
import com.delarosa.league.data.database.entities.League


@Database(entities = [League::class], version = 1,exportSchema = false)
abstract class PruebaDataBase : RoomDatabase() {

    companion object {
        fun build(context: Context) = Room.databaseBuilder(
            context,
            PruebaDataBase::class.java,
            "prueba-db"
        ).fallbackToDestructiveMigration().build()
    }
    abstract fun leagueDao(): LeagueDao
}