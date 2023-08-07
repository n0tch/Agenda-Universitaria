package com.core.data.mapper

import com.core.network.model.noteResponse.NoteResponse
import com.core.network.model.timetableResponse.TimetableResponse
import com.example.model.TimetableEntry

class TimetableMapper {

    fun mapToDomain(timetableResponse: TimetableResponse?) = TimetableEntry(
        id = timetableResponse?.id ?: "",
        weekDays = timetableResponse?.weekDays?.mapNotNull { it } ?: emptyList(),
        startTime = timetableResponse?.startTime ?: "",
        endTime = timetableResponse?.endTime ?: "",
        subjectId = timetableResponse?.subjectId ?: ""
    )

    fun mapListToDomain(timetableListResponse: List<TimetableResponse?>?) = timetableListResponse?.mapNotNull {
        mapToDomain(it)
    } ?: emptyList()

    fun mapToResponse(timetableEntry: TimetableEntry) = TimetableResponse(
        id = timetableEntry.id,
        weekDays = timetableEntry.weekDays,
        startTime = timetableEntry.startTime,
        endTime = timetableEntry.endTime,
        subjectId = timetableEntry.subjectId
    )
}