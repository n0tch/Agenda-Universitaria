package com.core.data.repository.exam

import com.core.data.mapper.ExamMapper
import com.core.network.exam.ExamDataProvider
import com.example.model.event.Exam
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ExamRepositoryImp @Inject constructor(
    private val examDataProvider: ExamDataProvider,
    private val examMapper: ExamMapper
) : ExamRepository {
    override fun saveExam(userId: String, exam: Exam): Flow<Exam> = flow {
        examDataProvider
            .saveExam(userId, examMapper.mapToResponse(exam))
            .map { examMapper.mapToDomain(it) }
            .collect { emit(it) }
    }

    override fun fetchAllExams(userId: String): Flow<List<Exam>> = flow {
        examDataProvider
            .fetchAllExams(userId)
            .map { responseList -> examMapper.mapListToDomain(responseList) }
            .collect { emit(it) }
    }

    override fun fetchExamById(userId: String, examId: String): Flow<Exam> = flow {
        examDataProvider
            .fetchExamById(userId, examId)
            .map { examMapper.mapToDomain(it) }
            .collect { emit(it) }
    }
}
