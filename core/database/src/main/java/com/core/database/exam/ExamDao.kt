package com.core.database.exam

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExamDao {

    @Insert
    suspend fun saveExam(examEntity: ExamEntity)

    @Delete
    fun deleteExam(examEntity: ExamEntity)

    @Query("SELECT * FROM exams")
    suspend fun fetchExams(): List<ExamEntity>

    @Query("SELECT * FROM exams WHERE exams.examId = :examId")
    suspend fun fetchExamById(examId: Int): ExamEntity

    @Query("SELECT * FROM exams WHERE exams.date > :fromDateInMillis")
    suspend fun fetchNextExams(fromDateInMillis: Long): List<ExamEntity>
}