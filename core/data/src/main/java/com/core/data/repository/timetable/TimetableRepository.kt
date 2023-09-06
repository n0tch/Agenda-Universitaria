package com.core.data.repository.timetable

import com.example.model.Timetable
import kotlinx.coroutines.flow.Flow
import java.time.DayOfWeek

interface TimetableRepository {

    fun saveTimetableEntry(userId: String, timetable: Timetable): Flow<Timetable>

    fun fetchTimetable(userId: String): Flow<Map<String, List<Timetable>>>

    fun fetchTimetableByDay(weekDay: DayOfWeek): Flow<List<Timetable>>

    fun fetchWeeklyTimetable(): Flow<Map<DayOfWeek, List<Timetable>>>
}
