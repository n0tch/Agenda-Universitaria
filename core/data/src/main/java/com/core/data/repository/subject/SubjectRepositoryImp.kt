package com.core.data.repository.subject

import com.core.data.extension.toSubject
import com.core.data.extension.toSubjectModel
import com.core.network.model.subjectResponse.SubjectModel
import com.core.network.subject.SubjectDataProvider
import com.example.model.Subject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SubjectRepositoryImp @Inject constructor(
    private val subjectDataProvider: SubjectDataProvider
): SubjectRepository {

    override fun saveSubject(userId: String, subject: Subject): Flow<String> = flow {
        subjectDataProvider.saveSubject(userId, subject.toSubjectModel()).collect {
            emit(it)
        }
    }

    override fun fetchSubjects(userId: String): Flow<List<Subject>> = flow {
        subjectDataProvider.fetchSubjects(userId).collect{ emit(it.toSubject()) }
    }
}
