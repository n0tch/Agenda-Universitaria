package com.core.database.exam

import com.core.database.databaseModel.ExamDataModel
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

interface ExamDao {

    fun saveExam(exam: ExamDataModel): Flow<ExamDataModel>

    fun fetchExams(): Flow<List<ExamDataModel>>

    fun deleteExam(examId: String): Flow<Boolean>

    fun fetchExamById(examId: String): Flow<ExamDataModel>

    fun fetchNextExams(fromLocalDate: LocalDateTime = LocalDateTime.now()): Flow<List<ExamDataModel>>
}