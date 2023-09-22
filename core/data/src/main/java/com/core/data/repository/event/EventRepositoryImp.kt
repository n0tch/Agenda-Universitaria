package com.core.data.repository.event

import android.util.Log
import com.core.database.event.EventDao
import com.core.database.event.notification.NotificationEntity
import com.core.database.event.relations.EventAndNotificationAndScoreAndSubject
import com.core.database.event.score.ScoreEntity
import com.core.notification.AppNotificationManager
import com.example.model.event.Event
import com.example.model.event.EventCompound
import com.example.model.event.EventNotification
import com.example.model.event.EventScore
import com.example.model.event.NotificationEarlier
import com.example.model.event.NotificationPeriod
import javax.inject.Inject

internal class EventRepositoryImp @Inject constructor(
    private val eventDao: EventDao,
    private val notificationManager: AppNotificationManager
): EventRepository {

    override suspend fun saveEvent(name: String, subjectId: Int, labelId: Int): Event {
        val event = Event(name = name)
        val id = eventDao.saveEvent(event.toEntity(subjectId, labelId))
        return event.copy(id = id.toInt())
    }

    override suspend fun fetchEvents(): List<EventCompound> {
        return eventDao.fetchCompoundEvents().map { it.toEventCompound() }
    }

    override suspend fun fetchEvents(limit: Int): List<EventCompound> {
        return eventDao.fetchCompoundEvents(limit).map { it.toEventCompound() }
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

fun EventNotification.toEntity() = NotificationEntity(
    notifyAt = notifyAt,
    eventId = eventId,
    notificationPeriod = notificationPeriod.name,
    notificationEarly = notificationEarly.name,
)
