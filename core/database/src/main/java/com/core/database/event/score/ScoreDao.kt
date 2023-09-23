package com.core.database.event.score

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface ScoreDao {
    @Insert
    suspend fun saveScore(scoreEntity: ScoreEntity)
}
