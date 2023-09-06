package com.core.data.repository.exam

import com.example.model.event.Exam
import kotlinx.coroutines.flow.Flow

interface ExamRepository {

    fun saveExam(exam: Exam): Flow<Exam>

    fun fetchAllExams(): Flow<List<Exam>>

    fun fetchExamById(examId: Int): Flow<Exam>

    fun fetchNextExams(fromDateTime: Long): Flow<List<Exam>>
}
