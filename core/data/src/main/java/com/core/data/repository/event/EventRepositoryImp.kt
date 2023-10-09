package com.core.data.repository.event

import com.core.database.event.EventDao
import com.core.database.event.notification.NotificationEntity
import com.core.database.event.score.ScoreEntity
import com.example.model.event.Event
import com.example.model.event.EventCompound
import com.example.model.event.EventNotification
import com.example.model.event.EventScore
import com.example.model.event.NotificationEarlier
import com.example.model.event.NotificationPeriod
import java.util.Calendar
import javax.inject.Inject

internal class EventRepositoryImp @Inject constructor(
    private val eventDao: EventDao
): EventRepository {

    override suspend fun saveEvent(event: Event, labelId: Int, subjectId: Int): Event {
        val id = eventDao.saveEvent(event.toEntity(subjectId, labelId))
        return event.copy(id = id.toInt())
    }

    override suspend fun fetchEvents(): List<EventCompound> {
        return eventDao.fetchCompoundEvents().map { it.toEventCompound() }
    }

    override suspend fun fetchEvents(limit: Int): List<EventCompound> {
        return eventDao.fetchCompoundEvents(limit).map { it.toEventCompound() }
    }

    override suspend fun saveNotification(notification: EventNotification?, eventId: Int) {
        notification?.toEntity(eventId)?.let { eventDao.saveNotification(it) }
    }

    override suspend fun fetchEventsGroupedByDate(): Map<Calendar, List<Event>> {
        return eventDao
            .fetchEventsGroupedByDate()
            .mapKeys { Calendar.getInstance().apply { timeInMillis = it.key } }
            .mapValues { it.value.map { eventEntity -> eventEntity.toEvent() } }
    }

    override suspend fun saveScore(score: EventScore?, eventId: Int) {
        score?.toEntity(eventId)?.let { eventDao.saveScore(it) }
    }

    override suspend fun fetchEventsBySubjectId(subjectId: Int): List<EventCompound> {
        return eventDao.fetchEventsBySubjectId(subjectId).map { it.toEventCompound() }
    }
}

fun EventScore.toEntity(eventId: Int) = ScoreEntity(
    score = score,
    eventId = eventId
)

//fun EventAndNotificationAndScoreAndSubject.toEventCompound() = EventCompound(
//    event = event.toEvent(),
//    eventGroup = null,
//    eventScore = score?.toEventScore(),
//    eventNotification = notification?.toEventNotification()
//)

fun NotificationEntity.toEventNotification() = EventNotification(
    id = notificationId,
    eventId = 1,
    notifyAt = notifyAt,
    notificationEarly = NotificationEarlier.valueOf(notificationEarly),
    notificationPeriod = NotificationPeriod.valueOf(notificationPeriod)
)

fun ScoreEntity.toEventScore() = EventScore(
    id = scoreId,
    eventId = eventId,
    score = score ?: 0F
)

fun EventNotification.toEntity(eventId: Int) = NotificationEntity(
    notifyAt = notifyAt,
    eventId = eventId,
    notificationPeriod = notificationPeriod.name,
    notificationEarly = notificationEarly.name,
)
