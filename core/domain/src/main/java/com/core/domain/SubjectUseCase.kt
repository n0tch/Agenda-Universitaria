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

    fun saveSubject(subject: Subject) = flow<Result<Subject>> {
        val savedSubject = subjectRepository.saveSubject(subject)
        emit(Result.Success(savedSubject))
    }.flowOn(ioDispatcher).catch {
        Log.e("saveSubject", it.message.toString())
        emit(Result.Error(it as Exception))
    }

    fun fetchSubjects() = flow<Result<List<Subject>>> {
        val subjects = subjectRepository.fetchSubjects()
        emit(Result.Success(subjects))
    }.flowOn(ioDispatcher).catch { emit(Result.Error(it as Exception)) }

    fun fetchSubject(subjectId: Int) = flow<Result<SubjectCompound>> {
        val subject = subjectRepository.fetchSubjectById(subjectId)
        emit(Result.Success(subject))
    }.flowOn(ioDispatcher)
        .catch { emit(Result.Error(it as Exception)) }

    fun deleteSubjectName(subject: Subject) = flow<Result<Boolean>> {
        val deleted = subjectRepository.deleteSubject(subject)
        emit(Result.Success(deleted))
    }.flowOn(ioDispatcher).catch {
        emit(Result.Error(it as Exception))
    }

    fun searchSubjectByName(query: String) = flow<Result<List<Subject>>> {
        val subjects = subjectRepository.searchSubjectsByName(query)
        emit(Result.Success(subjects))
    }.flowOn(ioDispatcher).catch {
        emit(Result.Error(it as Exception))
    }
}