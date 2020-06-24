package com.delarosa.common.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Event(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val homeText: String,
    val awayText: String,
    val date: String,
    val idTeam: String
)