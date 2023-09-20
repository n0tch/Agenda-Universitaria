package com.core.database.event

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class EventEntity(
    @PrimaryKey(autoGenerate = true) val eventId: Int = 0,
    val name: String?,
    val description: String?,
    val isGroupEvent: Boolean?,
    val subjectId: Int,
    val eventNotificationId: Int? = null,
    val scoreId: Int? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long? = null
)