package com.core.data.repository.timetable

import com.core.database.timetable.TimetableEntity
import com.example.model.Subject
import com.example.model.Timetable

internal fun Timetable.toEntity() = TimetableEntity(
    weekDay = weekDay,
    startTime = startTime,
    endTime = endTime,
    subjectId = subject?.id
)

internal fun TimetableEntity.toTimetable(subject: Subject?) = Timetable(
    id = timetableId,
    weekDay = weekDay ?: "",
    startTime = startTime ?: 0,
    endTime = endTime ?: 0,
    subject = subject,
)