package com.core.data.extension

import com.core.network.model.timetableResponse.TimetableResponse
import com.example.model.TimetableEntry

fun TimetableResponse.toTimetableEntity() = TimetableEntry(
    weekDays = weekDays.mapNotNull { it },
    startTime = startTime ?: "",
    endTime = endTime?: "",
    subjectId = subjectId ?: ""
)
