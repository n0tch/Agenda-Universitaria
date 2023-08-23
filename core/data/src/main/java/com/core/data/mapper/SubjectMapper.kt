package com.core.data.mapper

import com.core.database.databaseModel.SubjectDatabaseModel
import com.core.network.model.subjectResponse.SubjectResponse
import com.example.model.Subject

class SubjectMapper {

    fun mapToDomain(subjectResponse: SubjectResponse) = Subject(
        id = subjectResponse.id ?: "",
        name = subjectResponse.name ?: "",
        place = subjectResponse.placeName ?: "",
        teacher = subjectResponse.teacherName ?: ""
    )

    fun mapListToDomain(subjectResponseList: List<SubjectResponse>) = subjectResponseList.map {
        mapToDomain(it)
    }

    fun mapToResponse(subject: Subject) = SubjectResponse(
        id = subject.id,
        name = subject.name,
        placeName = subject.place,
        teacherName = subject.teacher
    )

    fun mapToDatabaseModel(subject: Subject) = SubjectDatabaseModel(
        id = subject.id,
        name = subject.name,
        place = subject.place,
        teacher = subject.teacher
    )
}
