package com.delarosa.common.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.delarosa.common.data.database.entities.Team

@Dao
interface TeamDao {

    @Query("SELECT * FROM Team WHERE idLeague = :code")
    fun getAll(code: String): List<Team>

    @Query("SELECT * FROM Team WHERE code = :code")
    fun findById(code: String): Team

    @Query("SELECT COUNT(id) FROM Team WHERE idLeague = :code")
    fun teamCount(code: String): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTeam(teams: List<Team>)

}