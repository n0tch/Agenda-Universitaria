package com.core.database.timetable

import com.core.database.databaseModel.TimetableDatabaseModel
import kotlinx.coroutines.flow.Flow

interface TimetableDao {

    fun saveTimetable(): Flow<Boolean>

    fun fetchTimetable(): Flow<Map<String, List<TimetableDatabaseModel>>>
}
