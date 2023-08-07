package com.core.domain

import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.common.Result
import com.core.data.repository.exam.ExamRepository
import com.core.data.repository.user.UserRepository
import com.example.model.event.Exam
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ExamUseCase @Inject constructor(
    @Dispatcher(AppDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
    private val examRepository: ExamRepository,
    private val userRepository: UserRepository
) {

    fun saveExam(exam: Exam): Flow<Result<Exam>> = flow {
        val userId = userRepository.fetchCurrentUser().id
        examRepository
            .saveExam(userId, exam)
            .flowOn(ioDispatcher)
            .catch { emit(Result.Error(it as Exception)) }
            .map { Result.Success(it) }
            .collect { emit(it) }
    }

    fun fetchAllExams(): Flow<Result<List<Exam>>> = flow {
        val userId = userRepository.fetchCurrentUser().id
        examRepository
            .fetchAllExams(userId)
            .catch { emit(Result.Error(it as Exception)) }
            .map { Result.Success(it) }
            .collect { emit(it) }
    }

    fun fetchExamById(examId: String): Flow<Result<Exam>> = flow {
        val userId = userRepository.fetchCurrentUser().id
        examRepository.fetchExamById(userId, examId)
            .catch { emit(Result.Error(it as Exception)) }
            .map { Result.Success(it) }
            .collect { emit(it) }
    }
}