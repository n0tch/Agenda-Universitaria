package com.core.network.exam

import com.core.network.helper.FirebaseDatabaseHelper
import com.core.network.model.examResponse.ExamResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ExamDataProvideImp @Inject constructor(
    private val firebaseDatabaseHelper: FirebaseDatabaseHelper
) : ExamDataProvider {

    override fun saveExam(userId: String, exam: ExamResponse): Flow<ExamResponse> = flow {
        val examResponse = firebaseDatabaseHelper.setData("$userId/$EXAM_PATH/", exam)
        emit(examResponse)
    }

    override fun fetchAllExams(userId: String): Flow<List<ExamResponse>> = flow {
        val items = firebaseDatabaseHelper.getDataList<ExamResponse>("$userId/$EXAM_PATH")
        emit(items)
    }

    override fun fetchExamById(userId: String, examId: String): Flow<ExamResponse> = flow {
        val exam = firebaseDatabaseHelper.getData<ExamResponse>("$userId/$EXAM_PATH/$examId")
        exam?.let { emit(it) }
    }

    companion object {
        private const val EXAM_PATH = "EXAMS"
    }
}
