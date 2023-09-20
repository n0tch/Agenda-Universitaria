package com.core.database.event.relations

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["eventId", "notificationId"])
data class EventNotificationCrossRef(
    @ColumnInfo(index = true)
    val eventId: Int,
    @ColumnInfo(index = true)
    val notificationId: Int
)
