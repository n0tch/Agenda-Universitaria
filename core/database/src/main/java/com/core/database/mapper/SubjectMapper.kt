package com.core.database.mapper

import com.core.database.databaseModel.SubjectDatabaseModel
import com.core.database.realmModel.SubjectRealm

class SubjectMapper {

    fun mapFromRealm(subjectRealm: SubjectRealm?) = SubjectDatabaseModel(
        id = subjectRealm?.id.toString(),
        name = subjectRealm?.name ?: "",
        place = subjectRealm?.place ?: "",
        teacher = subjectRealm?.teacher ?: ""
    )

    fun mapToRealm(subjectDatabaseModel: SubjectDatabaseModel) = SubjectRealm().apply {
        place = subjectDatabaseModel.place
        name = subjectDatabaseModel.name
        teacher = subjectDatabaseModel.teacher
    }
}