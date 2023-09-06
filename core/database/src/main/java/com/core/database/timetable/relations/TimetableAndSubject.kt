package com.core.database.timetable.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.core.database.subject.SubjectEntity
import com.core.database.timetable.TimetableEntity

data class TimetableAndSubject(
    @Embedded val timetableEntity: TimetableEntity,

    @Relation(
        parentColumn = "timetableId",
        entityColumn = "subjectId"
    )
    val subject: SubjectEntity
)
