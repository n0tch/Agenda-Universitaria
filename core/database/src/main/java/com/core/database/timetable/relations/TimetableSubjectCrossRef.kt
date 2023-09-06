package com.core.database.timetable.relations

import androidx.room.Entity

@Entity(primaryKeys = ["timetableId", "subjectId"])
data class TimetableSubjectCrossRef(
    val timetableId: Int,
    val subjectId: Int
)
