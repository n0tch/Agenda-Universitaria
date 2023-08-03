package com.core.data.repository.timetable

import com.example.model.TimetableEntry
import kotlinx.coroutines.flow.Flow

interface TimetableRepository {

    fun saveTimetableEntry(userId: String, timetableEntry: TimetableEntry): Flow<String>

    fun fetchTimetable(userId: String): Flow<List<TimetableEntry>>

    fun fetchTimetableByDay(userId: String, dayWeekName: String): Flow<String>
}