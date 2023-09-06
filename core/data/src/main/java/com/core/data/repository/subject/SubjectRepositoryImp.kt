package com.core.data.repository.subject

import com.core.database.subject.SubjectDao
import com.example.model.Subject
import com.example.model.SubjectCompound
import javax.inject.Inject

internal class SubjectRepositoryImp @Inject constructor(
    private val subjectDao: SubjectDao
): SubjectRepository {

    override suspend fun saveSubject(subject: Subject): Subject {
        val subjectId = subjectDao.saveSubject(subject.toEntity())
        return subject.copy(id = subjectId.toInt())
    }

    override suspend fun fetchSubjects(): List<Subject> {
        return subjectDao.fetchSubjects().map { it.toSubject() }
    }

    override suspend fun fetchSubjectById(subjectId: Int): SubjectCompound {
        return subjectDao.fetchSubjectById(subjectId).toSubjectCompound()
    }

    override suspend fun deleteSubject(subject: Subject): Boolean {
        val deleted = subjectDao.deleteSubject(subject.toEntity())
        return deleted == 1
    }
}
