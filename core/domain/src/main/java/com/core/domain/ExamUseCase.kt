package com.core.domain

import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.common.Result
import com.core.data.repository.exam.ExamRepository
import com.example.model.event.Exam
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import java.time.ZoneId
import javax.inject.Inject

class ExamUseCase @Inject constructor(
    @Dispatcher(AppDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
    private val examRepository: ExamRepository,
) {

    fun saveExam(exam: Exam): Flow<Result<Exam>> = flow {
        examRepository
            .saveExam(exam)
            .flowOn(ioDispatcher)
            .catch { emit(Result.Error(it as Exception)) }
            .map { Result.Success(it) }
            .collect { emit(it) }
    }

    fun fetchAllExams(): Flow<Result<List<Exam>>> = flow {
        examRepository
            .fetchAllExams()
            .catch { emit(Result.Error(it as Exception)) }
            .map { Result.Success(it) }
            .collect { emit(it) }
    }

    fun fetchExamById(examId: Int): Flow<Result<Exam>> = flow {
        examRepository.fetchExamById(examId)
            .catch { emit(Result.Error(it as
                    Exception)) }
            .map { Result.Success(it) }
            .collect { emit(it) }
    }

    fun fetchNextExams(fromDateTime: LocalDateTime = LocalDateTime.now()): Flow<Result<List<Exam>>> = flow {
        examRepository
            .fetchNextExams(fromDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
            .flowOn(ioDispatcher)
            .catch { emit(Result.Error(it as Exception)) }
            .map { Result.Success(it) }
            .collect{ emit(it) }
    }
}
