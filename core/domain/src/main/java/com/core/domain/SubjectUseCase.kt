package com.core.domain

import android.util.Log
import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.common.Result
import com.core.data.repository.subject.SubjectRepository
import com.example.model.Subject
import com.example.model.SubjectCompound
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
) {

    fun saveSubject(subject: Subject): Flow<Result<Subject>> = flow {
        subjectRepository
            .saveSubject(subject)
            .flowOn(ioDispatcher)
            .catch {
                Log.e("saveSubject", it.message.toString())
                emit(Result.Error(it as Exception))
            }
            .map { Result.Success(it) }
            .collect { emit(it) }
    }

    fun fetchSubjects(): Flow<Result<List<Subject>>> = flow {
        subjectRepository
            .fetchSubjects()
            .flowOn(ioDispatcher)
            .catch { emit(Result.Error(it as Exception)) }
            .map { Result.Success(it) }
            .collect { emit(it) }
    }

    fun fetchSubjectCompound(subjectId: Int): Flow<Result<SubjectCompound>> = flow {
        subjectRepository
            .fetchSubjectCompound(subjectId)
            .flowOn(ioDispatcher)
            .catch { emit(Result.Error(it as Exception)) }
            .map { Result.Success(it) }
            .collect { emit(it) }
    }

    fun deleteSubjectName(subjectId: Int): Flow<Boolean> = flow {
        subjectRepository.deleteSubject(subjectId).collect { emit(it) }
    }
}