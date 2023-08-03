package com.core.network.timetable

import com.core.network.model.timetableResponse.TimetableResponse
import kotlinx.coroutines.flow.Flow

interface TimetableDataProvider {

    fun saveTimetableEntry(userId: String, timetableResponse: TimetableResponse): Flow<String>

    fun fetchTimetables(userId: String): Flow<List<TimetableResponse>>

    fun fetchTimetableByDay(userId: String, dayWeekName: String): Flow<String>
}