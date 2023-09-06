package com.core.database.timetable

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TimetableDao {

    @Insert
    suspend fun saveTimetable(timetableEntity: TimetableEntity)

    @Query("SELECT * FROM timetables")
    suspend fun fetchTimetable(): List<TimetableEntity>

    @Query("SELECT * FROM timetables GROUP BY timetables.weekDay")
    suspend fun fetchTimetableGroupedByWeekDay(): List<TimetableEntity>

    @Query("SELECT * FROM timetables WHERE timetables.weekDay = :weekDay")
    suspend fun fetchTimetableByWeekDay(weekDay: String): List<TimetableEntity>
}
