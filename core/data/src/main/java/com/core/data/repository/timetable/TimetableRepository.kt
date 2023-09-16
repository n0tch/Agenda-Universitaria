package com.core.data.repository.timetable

import com.example.model.Timetable
import com.example.model.TimetableCompound
import kotlinx.coroutines.flow.Flow
import java.time.DayOfWeek

interface TimetableRepository {

    suspend fun saveTimetableEntry(timetables: List<Timetable>): List<Timetable>

    suspend fun fetchTimetable(): Map<String, List<TimetableCompound>>

    suspend fun fetchTimetableByDay(weekDay: DayOfWeek): List<TimetableCompound>

    suspend fun fetchWeeklyTimetable(): Map<DayOfWeek, List<TimetableCompound>>
}
