package com.core.data.repository.subject

import com.core.data.mapper.SubjectMapper
import com.core.network.subject.SubjectDataProvider
import com.example.model.Subject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SubjectRepositoryImp @Inject constructor(
    private val subjectDataProvider: SubjectDataProvider,
    private val subjectMapper: SubjectMapper
): SubjectRepository {

    override fun saveSubject(userId: String, subject: Subject): Flow<String> = flow {
        subjectDataProvider.saveSubject(userId, subjectMapper.mapToResponse(subject)).collect {
            emit(it)
        }
    }

    override fun fetchSubjects(userId: String): Flow<List<Subject>> = flow {
        subjectDataProvider.fetchSubjects(userId).collect{ emit(subjectMapper.mapListToDomain(it)) }
    }

    override fun deleteSubject(userId: String, subjectName: String): Flow<Boolean> = flow {
        subjectDataProvider.deactivateSubjects(userId, subjectName).collect { emit(it) }
    }
}
