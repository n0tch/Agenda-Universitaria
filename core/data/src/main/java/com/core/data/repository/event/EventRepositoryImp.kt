package com.core.data.repository.event

import com.core.database.event.EventDao
import com.core.database.event.notification.NotificationEntity
import com.core.database.event.relations.EventAndNotificationAndScoreAndSubject
import com.core.database.event.score.ScoreEntity
import com.example.model.event.EventCompound
import com.example.model.event.EventNotification
import com.example.model.event.EventScore
import com.example.model.event.NotificationEarlier
import com.example.model.event.NotificationPeriod
import javax.inject.Inject

internal class EventRepositoryImp @Inject constructor(
    private val eventDao: EventDao
): EventRepository {

    override suspend fun saveEvent(eventCompound: EventCompound, subjectId: Int): EventCompound {
        val id = eventDao.saveEvent(eventCompound.toEventEntity(subjectId))
        return eventCompound
    }

    override suspend fun fetchEvents(): List<EventCompound> {
        return eventDao.fetchEvents().map { it.toEventCompound() }
    }

    override suspend fun saveNotification(notification: EventNotification) {
        eventDao.saveNotification(notification.toEntity())
    }

    override suspend fun saveScore(score: EventScore) {
        eventDao.saveScore(score.toEntity())
    }

    override suspend fun fetchEventsBySubjectId(subjectId: Int): List<EventCompound> {
        return eventDao.fetchEventsBySubjectId(subjectId).map { it.toEventCompound() }
    }
}

fun EventScore.toEntity() = ScoreEntity(
    score = score,
    eventId = eventId
)

fun EventAndNotificationAndScoreAndSubject.toEventCompound() = EventCompound(
    event = event.toEvent(),
    eventGroup = null,
    eventScore = score?.toEventScore(),
    eventNotification = notification?.toEventNotification()
)

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

fun EventNotification.toEntity() = NotificationEntity(
    notifyAt = notifyAt,
    notificationPeriod = notificationPeriod.name,
    notificationEarly = notificationEarly.name,
)
