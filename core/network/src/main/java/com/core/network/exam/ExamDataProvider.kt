package com.core.network.exam

import com.core.network.model.examResponse.ExamResponse
import kotlinx.coroutines.flow.Flow

interface ExamDataProvider {

    fun saveExam(userId: String, exam: ExamResponse): Flow<ExamResponse>

    fun fetchAllExams(userId: String): Flow<List<ExamResponse>>

    fun fetchExamById(userId: String, examId: String): Flow<ExamResponse>
}
