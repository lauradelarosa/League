package com.delarosa.common.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class League(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val image: String,
    val code: String,
    val country: String
)