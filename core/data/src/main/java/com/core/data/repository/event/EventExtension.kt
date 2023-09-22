package com.core.data.repository.event

import com.core.data.repository.label.toLabel
import com.core.data.repository.subject.toSubject
import com.core.database.event.EventEntity
import com.core.database.event.relations.EventAndNotificationAndScoreAndSubject
import com.example.model.event.Event
import com.example.model.event.EventCompound

internal fun EventCompound.toEventEntity(subjectId: Int) = EventEntity(
    name = event.name,
    labelId = 1,
    subjectId = subjectId
)

fun Event.toEntity(subjectId: Int, labelId: Int) = EventEntity(
    name = name,
    subjectId = subjectId,
    labelId = labelId
)

fun EventAndNotificationAndScoreAndSubject.toEventCompound() = EventCompound(
    event = event.toEvent(),
    subject = subject.toSubject(),
    label = label.toLabel(),
    eventScore = score?.toEventScore(),
    eventNotification = notification?.toEventNotification()
)

fun EventEntity.toEvent() = Event(
    id = eventId,
    name = name ?: "",
    createdAt = createdAt
)
