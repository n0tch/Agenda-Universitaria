package com.core.data.repository.event

import com.core.data.repository.label.toLabel
import com.core.data.repository.subject.toSubject
import com.core.database.event.EventEntity
import com.core.database.event.relations.EventAndNotificationAndScoreAndSubject
import com.example.model.event.Event
import com.example.model.event.EventCompound

fun Event.toEntity(subjectId: Int, labelId: Int) = EventEntity(
    name = name,
    date = date,
    subjectId = subjectId,
    labelId = labelId,
    color = color
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
    date = date ?: 0L,
    createdAt = createdAt,
    color = color
)
