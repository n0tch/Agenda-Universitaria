package com.core.data.repository.subject

import com.core.database.subject.SubjectDao
import com.example.model.Subject
import com.example.model.SubjectCompound
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class SubjectRepositoryImp @Inject constructor(
    private val subjectDao: SubjectDao
): SubjectRepository {

    override fun saveSubject(subject: Subject): Flow<Subject> = flow {
        subjectDao.saveSubject(subject.toEntity())
        emit(subject)
    }

    override fun fetchSubjects(): Flow<List<Subject>> = flow {
        val subjects = subjectDao.fetchSubjects().map { it.toSubject() }
        emit(subjects)
    }

    override suspend fun fetchSubjectById(subjectId: Int): Subject {
        return Subject(1, "", "", "")
    }

    override fun fetchSubjectCompound(subjectId: Int): Flow<SubjectCompound> = flow {
//        val subjects = subjectDao.fetchCompoundSubjectsById(subjectId).toSubjectCompound()
//        Log.e("fetchSubjectCompound", subjects.toString())
//        emit(subjects)
    }

    override fun deleteSubject(subjectId: Int): Flow<Boolean> = flow {
        subjectDao.deleteSubject(subjectId)
        emit(true)
    }
}
