package com.core.network.subject

import com.core.network.helper.FirebaseDatabaseHelper
import com.core.network.model.SubjectSingleton
import com.core.network.model.subjectResponse.SubjectModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SubjectDataProviderImp @Inject constructor(
    private val firebaseDatabaseHelper: FirebaseDatabaseHelper,
    private val subjectSingleton: SubjectSingleton
) : SubjectDataProvider {

    override fun saveSubject(userId: String, subject: SubjectModel): Flow<String> = flow {
        val subjectId = firebaseDatabaseHelper.setData("$userId/$SUBJECT_PATH/", data = subject)
        emit(subjectId)
    }

    override fun fetchSubjects(userId: String): Flow<List<SubjectModel>> = flow {
        subjectSingleton.subjectList?.let {
            emit(it)
        } ?: run {
            val items = firebaseDatabaseHelper.getData<SubjectModel>(path = "$userId/$SUBJECT_PATH")
            subjectSingleton.subjectList = items
            emit(items)
        }
    }

    companion object {
        private const val SUBJECT_PATH = "SUBJECTS"
    }
}
