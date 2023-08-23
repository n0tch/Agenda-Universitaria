package com.core.data.repository.exam

import com.example.model.event.Exam
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

interface ExamRepository {

    fun saveExam(userId: String, exam: Exam): Flow<Exam>

    fun fetchAllExams(userId: String): Flow<List<Exam>>

    fun fetchExamById(userId: String, examId: String): Flow<Exam>

    fun fetchNextExams(fromDateTime: LocalDateTime): Flow<List<Exam>>
}
