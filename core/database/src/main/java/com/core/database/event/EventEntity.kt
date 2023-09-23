package com.core.database.event

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class EventEntity(
    @PrimaryKey(autoGenerate = true) val eventId: Int = 0,
    val name: String?,
    val subjectId: Int,
    val labelId: Int,
    val date: Long? = null,
    val color: String,
    val isGroupEvent: Boolean? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long? = null
)
