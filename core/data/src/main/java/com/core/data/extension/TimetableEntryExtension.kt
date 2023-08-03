package com.core.data.extension

import com.core.network.model.timetableResponse.TimetableResponse
import com.example.model.TimetableEntry

fun TimetableEntry.toTimetableResponse() = TimetableResponse(
    weekDays = weekDays,
    startTime = startTime,
    endTime = endTime,
    subjectId = subjectId
)