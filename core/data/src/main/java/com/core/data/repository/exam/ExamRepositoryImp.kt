package com.core.data.repository.exam

import com.core.database.exam.ExamDao
import com.example.model.event.Exam
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class ExamRepositoryImp @Inject constructor(
    private val examDao: ExamDao
) : ExamRepository {
    override fun saveExam(exam: Exam): Flow<Exam> = flow {
        examDao.saveExam(exam.toEntity())
        emit(exam)
    }

    override fun fetchAllExams(): Flow<List<Exam>> = flow {
        val exams = examDao.fetchExams().map { it.toExam() }
        emit(exams)
    }

    override fun fetchExamById(examId: Int): Flow<Exam> = flow {
        val exam = examDao.fetchExamById(examId).toExam()
        emit(exam)
    }

    override fun fetchNextExams(fromDateTime: Long): Flow<List<Exam>> = flow {
        val exams = examDao.fetchNextExams(fromDateTime).map { it.toExam() }
        emit(exams)
    }

    override suspend  fun fetchExamsCount(): Int {
        return examDao.fetchExamsCount()
    }
}
