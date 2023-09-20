package com.core.database.event.notification

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notification")
data class NotificationEntity(
    @PrimaryKey(autoGenerate = true) val notificationId: Int = 0,
    val notifyAt: Long,
    val notificationEarly: String,
    val notificationPeriod: String,
)
