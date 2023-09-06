package com.core.data.repository.subject

import com.example.model.Subject
import com.example.model.SubjectCompound

interface SubjectRepository {

    suspend fun saveSubject(subject: Subject): Subject

    suspend fun fetchSubjects(): List<Subject>

    suspend fun fetchSubjectById(subjectId: Int): SubjectCompound

    suspend fun deleteSubject(subject: Subject): Boolean
}