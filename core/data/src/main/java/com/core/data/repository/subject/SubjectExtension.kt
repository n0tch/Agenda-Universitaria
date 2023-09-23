package com.core.data.repository.subject

import com.core.data.repository.timetable.toTimetable
import com.core.database.subject.SubjectEntity
import com.core.database.subject.relations.SubjectWithTimetable
import com.example.model.Subject
import com.example.model.SubjectCompound
import java.time.DayOfWeek

internal fun Subject.toEntity() = SubjectEntity(
    name = name,
    place = place,
    teacher = teacher
)

internal fun SubjectEntity.toSubject() = Subject(
    id = subjectId,
    name = name ?: "",
    teacher = teacher ?: "",
    place = place ?: ""
)

internal fun SubjectWithTimetable.toSubjectCompound() = SubjectCompound(
//    subject = subject.toSubject(),
//    timetables = timetable
//        .groupBy { it.weekDay }
//        .mapKeys { DayOfWeek.valueOf(it.key ?: "") }
//        .mapValues { it.value.map { it.toTimetable() } }
)
