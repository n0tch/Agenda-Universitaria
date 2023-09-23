package com.core.database.subject

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.core.database.subject.relations.SubjectWithTimetable

@Dao
interface SubjectDao {

    @Insert
    suspend fun saveSubject(subjectEntity: SubjectEntity): Long

    @Query("SELECT * FROM subjects")
    suspend fun fetchSubjects(): List<SubjectEntity>

    @Transaction
    @Query("SELECT * FROM subjects WHERE subjects.subjectId = :subjectId")
    suspend fun fetchSubjectById(subjectId: Int): SubjectWithTimetable

    @Delete
    suspend fun deleteSubject(subject: SubjectEntity): Int

//    @Transaction
//    @Query("SELECT * FROM subjects WHERE subjects.uid = :subjectId")
//    suspend fun fetchCompoundSubjectsById(subjectId: Int): SubjectCompoundEntity

    @Transaction
    @Query("SELECT * FROM subjects WHERE subjects.subjectId = :subjectId")
    suspend fun fetchSubjectWithNotes(subjectId: Int): SubjectWithTimetable

    @Query("SELECT * FROM subjects WHERE name LIKE '%' || :query || '%'")
    suspend fun searchSubjectByName(query: String): List<SubjectEntity>
}
