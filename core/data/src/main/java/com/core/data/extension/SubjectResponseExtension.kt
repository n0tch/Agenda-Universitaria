package com.core.data.extension

import com.core.network.model.subjectResponse.SubjectModel
import com.example.model.Subject

fun List<SubjectModel>.toSubject() = map {
    Subject(
        name = it.name ?: "",
    )
}