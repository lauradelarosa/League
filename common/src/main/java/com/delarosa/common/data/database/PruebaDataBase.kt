package com.delarosa.common.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.delarosa.common.data.database.dao.EventDao
import com.delarosa.common.data.database.dao.LeagueDao
import com.delarosa.common.data.database.dao.TeamDao
import com.delarosa.common.data.database.entities.Event
import com.delarosa.common.data.database.entities.League
import com.delarosa.common.data.database.entities.Team

@Database(entities = [League::class, Team::class, Event::class], version = 1)
abstract class PruebaDataBase : RoomDatabase() {

    companion object {
        fun build(context: Context) = Room.databaseBuilder(
            context,
            PruebaDataBase::class.java,
            "prueba-db"
        ).build()
    }

    abstract fun leagueDao(): LeagueDao
    abstract fun teamDao(): TeamDao
    abstract fun eventDao(): EventDao
}