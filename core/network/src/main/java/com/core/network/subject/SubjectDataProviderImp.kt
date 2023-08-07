package com.core.network.subject

import com.core.network.helper.FirebaseDatabaseHelper
import com.core.network.model.singleton.SubjectSingleton
import com.core.network.model.subjectResponse.SubjectResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SubjectDataProviderImp @Inject constructor(
    private val firebaseDatabaseHelper: FirebaseDatabaseHelper,
    private val subjectSingleton: SubjectSingleton
) : SubjectDataProvider {

    override fun saveSubject(userId: String, subject: SubjectResponse): Flow<String> = flow {
        val subjectId = firebaseDatabaseHelper.setData("$userId/$SUBJECT_PATH/", data = subject)
        subjectSingleton.subjectList?.add(subject)
        emit(subjectId.id ?: "")
    }

    override fun fetchSubjects(userId: String): Flow<List<SubjectResponse>> = flow {
        subjectSingleton.subjectList?.let {
            emit(it)
        } ?: run {
            val items = firebaseDatabaseHelper.getDataList<SubjectResponse>(path = "$userId/$SUBJECT_PATH")
            subjectSingleton.subjectList?.apply { clear() }?.addAll(items)
            emit(items)
        }
    }

    override fun deactivateSubjects(userId: String, subjectName: String): Flow<Boolean> = flow {
        firebaseDatabaseHelper.deleteData("$userId/$SUBJECT_PATH/$subjectName")
        emit(true)
    }

    companion object {
        private const val SUBJECT_PATH = "SUBJECTS"
    }
}
