package com.core.data.repository.event

import com.core.database.event.EventEntity
import com.example.model.event.Event
import com.example.model.event.EventCompound

fun EventCompound.toEventEntity(subjectId: Int) = EventEntity(
    name = event.name,
    description = event.description,
    scoreId = 1,
    isGroupEvent = event.isGroupEvent,
    eventNotificationId = 1,
    subjectId = subjectId
)

fun EventEntity.toEventCompound() = EventCompound(
    event = Event(
        id = eventId,
        name = name ?: "",
        description = description ?: "",
        eventLabels = emptyList(),
        hasScore = false,
        isGroupEvent = isGroupEvent ?: false,
        notificationOn = true,
        createdAt = createdAt
    )
)

fun EventEntity.toEvent() = Event(
    id = eventId,
    name = name ?: "",
    description = description ?: "",
    eventLabels = listOf("Prova"),
    hasScore = false,
    isGroupEvent = isGroupEvent ?: false,
    notificationOn = true,
    createdAt = createdAt
)
