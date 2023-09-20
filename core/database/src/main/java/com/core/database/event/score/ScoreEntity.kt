package com.core.database.event.score

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "score")
data class ScoreEntity(
    @PrimaryKey(autoGenerate = true) val scoreId: Int = 0,
    val score: Float?,
    val eventId: Int
)
