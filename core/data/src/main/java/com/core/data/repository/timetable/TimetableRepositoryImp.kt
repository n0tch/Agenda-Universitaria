package com.core.data.repository.timetable

import com.core.database.timetable.TimetableDao
import com.example.model.Timetable
import com.example.model.TimetableCompound
import java.time.DayOfWeek
import javax.inject.Inject

internal class TimetableRepositoryImp @Inject constructor(
    private val timetableDao: TimetableDao
) : TimetableRepository {

    override suspend fun saveTimetableEntry(timetables: List<Timetable>, subjectId: Int): List<Timetable> {
        val ids = timetableDao.saveTimetables(timetables.map { it.toEntityWithGivenSubjectId(subjectId) })
        return listOf()
    }

    override suspend fun fetchTimetable(): Map<String, List<TimetableCompound>> {
        return timetableDao.fetchTimetable().map { it.toTimetableCompound() }
            .groupBy { it.timetable.weekDay }
    }

    override suspend fun fetchTimetableByDay(
        weekDay: DayOfWeek
    ): List<TimetableCompound> {
        return timetableDao.fetchTimetableByWeekDay(weekDay.name).map { it.toTimetableCompound() }
    }

    override suspend fun fetchWeeklyTimetable(): Map<DayOfWeek, List<TimetableCompound>> {
        val timetables = mutableMapOf<DayOfWeek, List<TimetableCompound>>()
        DayOfWeek.values().forEach { dayOfWeek ->
            val timetable = timetableDao.fetchTimetableByWeekDay(dayOfWeek.name)
                .map { it.toTimetableCompound() }

            timetables[dayOfWeek] = timetable
        }
        return timetables
    }

    override suspend fun fetchTimetableBySubjectId(subjectId: Int): Map<DayOfWeek, List<Timetable>> {
        return timetableDao
            .fetchTimetableBySubjectId(subjectId)
            .groupBy { it.weekDay }
            .mapKeys { DayOfWeek.valueOf(it.key ?: "") }
            .mapValues { it.value.map { it.toTimetable() } }
    }
}
