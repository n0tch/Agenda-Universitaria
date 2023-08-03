package com.core.network.subject

import com.core.network.helper.FirebaseDatabaseHelper
import com.core.network.model.SubjectSingleton
import com.core.network.model.subjectResponse.SubjectModel
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SubjectDataProviderImp @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    private val firebaseDatabaseHelper: FirebaseDatabaseHelper,
    private val subjectSingleton: SubjectSingleton
) : SubjectDataProvider {

    override fun saveSubject(userId: String, subject: SubjectModel): Flow<String> = flow {
        val subjectId = firebaseDatabaseHelper.setData("$userId/$SUBJECT_PATH/", data = subject)
        subjectSingleton.subjectList?.add(subject)
        emit(subjectId)
    }

    override fun fetchSubjects(userId: String): Flow<List<SubjectModel>> = flow {
        subjectSingleton.subjectList?.let {
            emit(it)
        } ?: run {
            val items = firebaseDatabaseHelper.getData<SubjectModel>(path = "$userId/$SUBJECT_PATH")
            subjectSingleton.subjectList?.apply { clear() }?.addAll(items)
            emit(items)
        }
    }

    override fun deactivateSubjects(userId: String, subjectName: String): Flow<Boolean> = flow {
        firebaseDatabase
            .getReference("$userId/$SUBJECT_PATH/$subjectName")
            .removeValue()
            .await()

        emit(true)
    }

    companion object {
        private const val SUBJECT_PATH = "SUBJECTS"
    }
}
