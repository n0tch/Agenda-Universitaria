package com.core.data.repository.subject

import com.core.data.mapper.SubjectMapper
import com.core.database.subject.SubjectDao
import com.example.model.Subject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SubjectRepositoryImp @Inject constructor(
    private val subjectDao: SubjectDao,
    private val subjectMapper: SubjectMapper
): SubjectRepository {

    override fun saveSubject(userId: String, subject: Subject): Flow<String> = flow {
        subjectDao
            .saveSubject(subjectMapper.mapToDatabaseModel(subject))
            .collect { subject -> emit(subject?.id ?: "") }
    }

    override fun fetchSubjects(userId: String): Flow<List<Subject>> = flow {
        subjectDao
            .fetchSubjects()
            .map{
                it.map { subject ->
                    Subject(subject.id, subject.name, subject.place, subject.teacher)
                }
            }
            .collect{ emit(it) }
    }

    override fun deleteSubject(userId: String, subjectName: String): Flow<Boolean> = flow {
        subjectDao
            .deleteSubject(subjectName)
            .collect { emit(it) }
    }
}
