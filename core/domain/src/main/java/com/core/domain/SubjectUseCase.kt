package com.core.domain

import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.common.Result
import com.core.data.repository.subject.SubjectRepository
import com.core.data.repository.user.UserRepository
import com.example.model.Subject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SubjectUseCase @Inject constructor(
    @Dispatcher(AppDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
    private val subjectRepository: SubjectRepository,
    private val userRepository: UserRepository
) {

    fun saveSubject(subject: Subject): Flow<Result<String>> = flow {
        val userId = userRepository.fetchCurrentUser().id
        subjectRepository
            .saveSubject(userId, subject)
            .flowOn(ioDispatcher)
            .catch { emit(Result.Error(it as Exception)) }
            .map { Result.Success(it) }
            .collect { emit(it) }
    }

    fun getSubjects(): Flow<Result<List<Subject>>> = flow {
        val userId = userRepository.fetchCurrentUser().id
        subjectRepository
            .fetchSubjects(userId)
            .flowOn(ioDispatcher)
            .catch { emit(Result.Error(it as Exception)) }
            .map { Result.Success(it) }
            .collect { emit(it) }
    }

    fun deleteSubjectName(subjectName: String): Flow<Boolean> = flow {
        val userId = userRepository.fetchCurrentUser().id
        subjectRepository.deleteSubject(userId, subjectName).collect { emit(it) }
    }
}