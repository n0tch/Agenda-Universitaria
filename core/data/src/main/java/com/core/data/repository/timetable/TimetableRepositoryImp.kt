package com.core.data.repository.timetable

import com.core.data.repository.subject.toSubject
import com.core.database.subject.SubjectDao
import com.core.database.timetable.TimetableDao
import com.example.model.Timetable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.DayOfWeek
import javax.inject.Inject

internal class TimetableRepositoryImp @Inject constructor(
    private val timetableDao: TimetableDao,
    private val subjectDao: SubjectDao
) : TimetableRepository {

    override fun saveTimetableEntry(
        userId: String,
        timetable: Timetable
    ): Flow<Timetable> = flow {
        timetableDao.saveTimetable(timetable.toEntity())
        emit(timetable)
    }

    override fun fetchTimetable(
        userId: String
    ): Flow<Map<String, List<Timetable>>> = flow {
        val timetables = timetableDao.fetchTimetable().map { entity ->
            val subject = subjectDao.fetchSubjectById(entity.subjectId)
            entity.toTimetable(subject.subject.toSubject())
        }.groupBy { it.weekDay }

        emit(timetables)
    }

    override fun fetchTimetableByDay(
        weekDay: DayOfWeek
    ): Flow<List<Timetable>> = flow {
        val timetable = timetableDao.fetchTimetableByWeekDay(weekDay.name).map { entity ->
            val subjectEntity = subjectDao.fetchSubjectById(entity.subjectId)
            entity.toTimetable(subjectEntity.subject.toSubject())
        }

        emit(timetable)
    }

    override fun fetchWeeklyTimetable(): Flow<Map<DayOfWeek, List<Timetable>>> = flow {
        val timetables = mutableMapOf<DayOfWeek, List<Timetable>>()
        DayOfWeek.values().forEach { dayOfWeek ->
            val timetable = timetableDao.fetchTimetableByWeekDay(dayOfWeek.name).map { entity ->
                val subjectEntity = subjectDao.fetchSubjectById(entity.subjectId)
                entity.toTimetable(subjectEntity.subject.toSubject())
            }

            timetables[dayOfWeek] = timetable
        }
        emit(timetables)
    }
}
