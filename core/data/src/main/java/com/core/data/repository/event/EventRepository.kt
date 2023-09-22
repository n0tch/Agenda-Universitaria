package com.core.data.repository.event

import com.example.model.event.Event
import com.example.model.event.EventCompound
import com.example.model.event.EventNotification
import com.example.model.event.EventScore

interface EventRepository {

    suspend fun saveEvent(name: String, subjectId: Int, labelId: Int): Event

    suspend fun fetchEvents(): List<EventCompound>

    suspend fun fetchEvents(limit: Int): List<EventCompound>

    suspend fun fetchEventsBySubjectId(subjectId: Int): List<EventCompound>

    suspend fun saveNotification(notification: EventNotification)

    suspend fun saveScore(score: EventScore)
}