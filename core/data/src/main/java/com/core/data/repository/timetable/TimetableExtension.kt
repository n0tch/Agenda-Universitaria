package com.core.data.repository.timetable

import com.core.data.repository.subject.toSubject
import com.core.database.timetable.TimetableEntity
import com.core.database.timetable.relations.TimetableAndSubject
import com.example.model.Timetable
import com.example.model.TimetableCompound

internal fun Timetable.toEntity() = TimetableEntity(
    weekDay = weekDay,
    startTime = startTime,
    endTime = endTime,
    subjectId = subjectId
)

internal fun TimetableEntity.toTimetable() = Timetable(
    id = timetableId,
    weekDay = weekDay ?: "",
    startTime = startTime ?: 0,
    endTime = endTime ?: 0,
    subjectId = subjectId ?: 0,
)

internal fun TimetableAndSubject.toTimetableCompound() = TimetableCompound(
    timetable = timetableEntity.toTimetable(),
    subject = subject.toSubject()
)