package com.core.data.repository.event

import com.example.model.event.Event
import com.example.model.event.EventCompound
import com.example.model.event.EventNotification
import com.example.model.event.EventScore
import java.util.Calendar

interface EventRepository {

    suspend fun saveEvent(event: Event, labelId: Int, subjectId: Int): Event

    suspend fun fetchEvents(): List<EventCompound>

    suspend fun fetchEvents(limit: Int): List<EventCompound>

    suspend fun fetchEventsBySubjectId(subjectId: Int): List<EventCompound>

    suspend fun fetchEventsGroupedByDate(): Map<Calendar, List<Event>>

    suspend fun saveNotification(notification: EventNotification?, eventId: Int)

    suspend fun saveScore(score: EventScore?, eventId: Int)
}
