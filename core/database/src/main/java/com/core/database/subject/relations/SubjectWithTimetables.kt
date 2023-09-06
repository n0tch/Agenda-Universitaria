package com.core.database.subject.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.core.database.subject.SubjectEntity
import com.core.database.timetable.TimetableEntity
import com.core.database.timetable.relations.TimetableSubjectCrossRef

data class SubjectWithTimetables(
    @Embedded val subject: SubjectEntity,
    @Relation(
        parentColumn = "subjectId",
        entityColumn = "timetableId",
        associateBy = Junction(TimetableSubjectCrossRef::class)
    )
    val timetables: List<TimetableEntity>
)
