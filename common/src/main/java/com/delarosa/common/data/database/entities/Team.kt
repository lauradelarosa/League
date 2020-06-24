package com.delarosa.common.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Team(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val code: String,
    val idLeague: String,
    val name: String,
    val description: String,
    val foundationYear: String,
    val teamBadge: String,
    val teamJersey: String,
    val stadium: String,
    val website: String,
    val facebook: String,
    val youtube: String,
    val twitter: String,
    val instagram: String
)