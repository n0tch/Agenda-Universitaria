package com.core.data.repository.timetable

import com.core.data.mapper.TimetableMapper
import com.core.network.timetable.TimetableDataProvider
import com.example.model.TimetableEntry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TimetableRepositoryImp @Inject constructor(
    private val timetableDataProvider: TimetableDataProvider,
    private val timetableMapper: TimetableMapper
): TimetableRepository {
    override fun saveTimetableEntry(userId: String, timetableEntry: TimetableEntry): Flow<String>  = flow {
        timetableDataProvider.saveTimetableEntry(userId, timetableMapper.mapToResponse(timetableEntry)).collect{ emit("sasasa") }
    }

    override fun fetchTimetable(userId: String): Flow<List<TimetableEntry>> = flow {
        timetableDataProvider
            .fetchTimetables(userId)
            .map {timetableMapper.mapListToDomain(it) }
            .collect { emit(it) }
    }

    override fun fetchTimetableByDay(userId: String, dayWeekName: String): Flow<String> = flow {
        timetableDataProvider.fetchTimetableByDay(userId, dayWeekName).collect { emit(it) }
    }
}
