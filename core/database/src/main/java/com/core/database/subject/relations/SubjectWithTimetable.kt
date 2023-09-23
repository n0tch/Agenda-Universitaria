package com.core.database.subject.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.core.database.subject.SubjectEntity
import com.core.database.timetable.TimetableEntity

data class SubjectWithTimetable(
    @Embedded val subject: SubjectEntity,

    @Relation(
        parentColumn = "subjectId",
        entityColumn = "subjectId"
    )
    val timetable: List<TimetableEntity> = emptyList()
)
