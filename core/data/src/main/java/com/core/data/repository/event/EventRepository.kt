package com.core.data.repository.event

import com.example.model.event.EventCompound
import com.example.model.event.EventNotification
import com.example.model.event.EventScore

interface EventRepository {

    suspend fun saveEvent(eventCompound: EventCompound, subjectId: Int): EventCompound

    suspend fun fetchEvents(): List<EventCompound>

    suspend fun fetchEventsBySubjectId(subjectId: Int): List<EventCompound>

    suspend fun saveNotification(notification: EventNotification)

    suspend fun saveScore(score: EventScore)
}