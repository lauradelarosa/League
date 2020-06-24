package com.delarosa.common.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.delarosa.common.data.database.entities.League

@Dao
interface LeagueDao {

    @Query("SELECT * FROM League")
    fun getAll(): List<League>

    @Query("SELECT * FROM League WHERE id = :id")
    fun findById(id: Int): League

    @Query("SELECT COUNT(id) FROM League")
    fun leagueCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertLeague(league: List<League>)

}