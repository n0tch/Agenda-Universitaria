package com.core.database.subject

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.core.database.subject.relations.SubjectWithNotes

@Dao
interface SubjectDao {

    @Insert
    suspend fun saveSubject(subjectEntity: SubjectEntity)

    @Query("SELECT * FROM subjects")
    suspend fun fetchSubjects(): List<SubjectEntity>

    @Query("SELECT * FROM subjects WHERE subjects.subjectId = :subjectId")
    suspend fun fetchSubjectById(subjectId: Int?): SubjectEntity?

    @Query("DELETE FROM subjects WHERE subjects.subjectId = :subjectId")
    suspend fun deleteSubject(subjectId: Int)

//    @Transaction
//    @Query("SELECT * FROM subjects WHERE subjects.uid = :subjectId")
//    suspend fun fetchCompoundSubjectsById(subjectId: Int): SubjectCompoundEntity

    @Transaction
    @Query("SELECT * FROM subjects WHERE subjects.subjectId = :subjectId")
    suspend fun fetchSubjectWithNotes(subjectId: Int): SubjectWithNotes
}
