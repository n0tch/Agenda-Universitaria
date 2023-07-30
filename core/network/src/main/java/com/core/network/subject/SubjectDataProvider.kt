package com.core.network.subject

import com.core.network.model.subjectResponse.SubjectModel
import kotlinx.coroutines.flow.Flow

interface SubjectDataProvider {

    fun saveSubject(userId: String, subject: SubjectModel): Flow<String>

    fun fetchSubjects(userId: String): Flow<List<SubjectModel>>

}