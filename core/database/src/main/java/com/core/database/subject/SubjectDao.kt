package com.core.database.subject

import com.core.database.databaseModel.SubjectDatabaseModel
import kotlinx.coroutines.flow.Flow

interface SubjectDao {

    fun saveSubject(subjectDatabaseModel: SubjectDatabaseModel): Flow<SubjectDatabaseModel?>

    fun fetchSubjects(): Flow<List<SubjectDatabaseModel>>

    fun deleteSubject(subjectId: String): Flow<Boolean>
}
