package com.core.data.repository.subject

import com.example.model.Subject
import com.example.model.SubjectCompound
import kotlinx.coroutines.flow.Flow

interface SubjectRepository {

    fun saveSubject(subject: Subject): Flow<Subject>

    fun fetchSubjects(): Flow<List<Subject>>

    suspend fun fetchSubjectById(subjectId: Int): Subject

    fun fetchSubjectCompound(subjectId: Int): Flow<SubjectCompound>

    fun deleteSubject(subjectId: Int): Flow<Boolean>
}