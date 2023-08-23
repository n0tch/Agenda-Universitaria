package com.core.data.repository.exam

import com.core.data.mapper.ExamMapper
import com.core.database.exam.ExamDao
import com.example.model.event.Exam
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import javax.inject.Inject

class ExamRepositoryImp @Inject constructor(
    private val examDao: ExamDao,
    private val examMapper: ExamMapper
) : ExamRepository {
    override fun saveExam(userId: String, exam: Exam): Flow<Exam> = flow {
        examDao
            .saveExam(examMapper.mapToDatabaseModel(exam))
            .map { examMapper.mapToDomain(it) }
            .collect { emit(it) }
    }

    override fun fetchAllExams(userId: String): Flow<List<Exam>> = flow {
        examDao
            .fetchExams()
            .map { it.map { exam -> examMapper.mapToDomain(exam) } }
            .collect { emit(it) }
    }

    override fun fetchExamById(userId: String, examId: String): Flow<Exam> = flow {
        examDao
            .fetchExamById(examId)
            .map { examMapper.mapToDomain(it) }
            .collect { emit(it) }
    }

    override fun fetchNextExams(fromDateTime: LocalDateTime): Flow<List<Exam>> = flow {
        examDao
            .fetchNextExams()
            .map { it.map { exam -> examMapper.mapToDomain(exam) } }
            .collect { emit(it) }
    }
}
