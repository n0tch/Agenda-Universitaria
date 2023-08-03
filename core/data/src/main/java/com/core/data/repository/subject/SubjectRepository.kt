package com.core.data.repository.subject

import com.example.model.Subject
import kotlinx.coroutines.flow.Flow

interface SubjectRepository {

    fun saveSubject(userId: String, subject: Subject): Flow<String>

    fun fetchSubjects(userId: String): Flow<List<Subject>>

    fun deleteSubject(userId: String, subjectName: String): Flow<Boolean>
}