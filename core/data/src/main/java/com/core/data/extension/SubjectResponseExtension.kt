package com.core.data.extension

import com.core.network.model.subjectResponse.SubjectModel
import com.example.model.Subject

fun List<SubjectModel>.toSubject() = map {
    Subject(
        id = it.id ?: "",
        name = it.name ?: "",
        place = it.placeName ?: "",
        teacher = it.teacherName ?: ""
    )
}