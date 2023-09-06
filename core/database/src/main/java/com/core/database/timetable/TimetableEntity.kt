package com.core.database.timetable

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "timetables")
data class TimetableEntity(
    @PrimaryKey(autoGenerate = true) val timetableId: Int = 0,
    val weekDay: String?,
    val startTime: Long?,
    val endTime: Long?,
    val subjectId: Int?
)
