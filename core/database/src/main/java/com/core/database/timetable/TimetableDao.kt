package com.core.database.timetable

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.core.database.timetable.relations.TimetableAndSubject

@Dao
interface TimetableDao {

    @Insert
    suspend fun saveTimetable(timetableEntity: TimetableEntity): Long

    @Insert
    suspend fun saveTimetables(timetables: List<TimetableEntity>): List<Long>

    @Transaction
    @Query("SELECT * FROM timetables")
    suspend fun fetchTimetable(): List<TimetableAndSubject>

    @Transaction
    @Query("SELECT * FROM timetables WHERE timetables.weekDay = :weekDay")
    suspend fun fetchTimetableByWeekDay(weekDay: String): List<TimetableAndSubject>

    @Query("SELECT * FROM timetables WHERE subjectId = :subjectId")
    suspend fun fetchTimetableBySubjectId(subjectId: Int): List<TimetableEntity>
}
