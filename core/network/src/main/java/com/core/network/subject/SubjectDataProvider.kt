package com.core.network.subject

import com.core.network.model.subjectResponse.SubjectResponse
import kotlinx.coroutines.flow.Flow

interface SubjectDataProvider {

    fun saveSubject(userId: String, subject: SubjectResponse): Flow<String>

    fun fetchSubjects(userId: String): Flow<List<SubjectResponse>>

    fun deactivateSubjects(userId: String, subjectName: String): Flow<Boolean>
}
