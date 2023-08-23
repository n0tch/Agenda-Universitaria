package com.core.database.exam

import com.core.database.databaseModel.ExamDataModel
import com.core.database.extension.epochSecond
import com.core.database.mapper.ExamMapper
import com.core.database.realmModel.ExamRealm
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.asFlow
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.Sort
import io.realm.kotlin.types.RealmInstant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import javax.inject.Inject

class ExamDaoImp @Inject constructor(
    private val realm: Realm,
    private val examMapper: ExamMapper
) : ExamDao {

    override fun saveExam(exam: ExamDataModel): Flow<ExamDataModel> = flow {
        realm
            .write { copyToRealm(examMapper.mapToRealm(exam)) }
            .asFlow()
            .map { examMapper.mapFromRealm(it.obj) }
            .collect { emit(it) }
    }

    override fun fetchExams(): Flow<List<ExamDataModel>> = flow {
        realm
            .query<ExamRealm>()
            .find()
            .asFlow()
            .map { it.list.map { exam -> examMapper.mapFromRealm(exam) } }
            .collect { emit(it) }
    }

    override fun deleteExam(examId: String): Flow<Boolean> = flow {
        realm.writeBlocking {
            val exam = query<ExamRealm>("id == $examId").find().first()
            delete(exam)
        }
        emit(true)
    }

    override fun fetchExamById(examId: String): Flow<ExamDataModel> = flow {
        val exam = realm
            .query<ExamRealm>("id == $examId")
            .find()
            .first()

        emit(examMapper.mapFromRealm(exam))
    }

    override fun fetchNextExams(fromLocalDate: LocalDateTime): Flow<List<ExamDataModel>> = flow {
        realm.query<ExamRealm>(
            "date > $0",
            RealmInstant.from(
                epochSeconds = fromLocalDate.epochSecond(),
                nanosecondAdjustment = 0
            )
        ).sort("date", Sort.ASCENDING).asFlow()
            .map { it.list.map { exam -> examMapper.mapFromRealm(exam) } }
            .collect {
                emit(it)
            }
    }
}