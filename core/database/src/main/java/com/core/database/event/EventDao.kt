package com.core.database.event

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.core.database.event.notification.NotificationEntity
import com.core.database.event.relations.EventAndNotificationAndScoreAndSubject
import com.core.database.event.score.ScoreEntity

@Dao
interface EventDao {

    @Insert
    suspend fun saveEvent(eventEntity: EventEntity): Long

    @Query("SELECT * FROM events")
    suspend fun fetchEvents(): List<EventEntity>

    @Transaction
    @Query("SELECT * FROM events")
    suspend fun fetchCompoundEvents(): List<EventAndNotificationAndScoreAndSubject>

    @Transaction
    @Query("SELECT * FROM events ORDER BY events.createdAt LIMIT :limit")
    suspend fun fetchCompoundEvents(limit: Int): List<EventAndNotificationAndScoreAndSubject>

    @Query("SELECT * FROM events WHERE events.subjectId = :subjectId")
    suspend fun fetchEventsBySubjectId(subjectId: Int): List<EventAndNotificationAndScoreAndSubject>

    //Notification
    @Insert
    suspend fun saveNotification(notificationEntity: NotificationEntity): Long

    //Score
    @Insert
    suspend fun saveScore(scoreEntity: ScoreEntity)
}