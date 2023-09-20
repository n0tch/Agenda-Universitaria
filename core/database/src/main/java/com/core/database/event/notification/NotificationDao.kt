package com.core.database.event.notification

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface NotificationDao {

    @Insert
    suspend fun saveNotification(notificationEntity: NotificationEntity): Long
}