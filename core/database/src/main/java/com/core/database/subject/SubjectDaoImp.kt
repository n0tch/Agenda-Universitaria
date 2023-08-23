package com.core.database.subject

import com.core.database.databaseModel.SubjectDatabaseModel
import com.core.database.mapper.SubjectMapper
import com.core.database.realmModel.SubjectRealm
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.asFlow
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject

class SubjectDaoImp @Inject constructor(
    private val realm: Realm
) : SubjectDao {

    override fun saveSubject(subjectDatabaseModel: SubjectDatabaseModel): Flow<SubjectDatabaseModel?> =
        flow {
            realm
                .write { copyToRealm(SubjectMapper().mapToRealm(subjectDatabaseModel)) }
                .asFlow()
                .catch { throw Exception() }
                .map { SubjectMapper().mapFromRealm(it.obj) }
                .collect { emit(it) }
        }

    override fun fetchSubjects(): Flow<List<SubjectDatabaseModel>> = flow {
        realm
            .query<SubjectRealm>()
            .find()
            .asFlow()
            .catch { throw Exception() }
            .mapNotNull { it.list.map { SubjectMapper().mapFromRealm(it) } }
            .collect { emit(it) }
    }

    override fun deleteSubject(subjectId: String): Flow<Boolean> = flow {
        realm.writeBlocking {
            val subject = query<SubjectRealm>("id == $subjectId").find().first()
            delete(subject)
        }
        emit(true)
    }
}