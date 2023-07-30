package com.core.data.extension

import com.core.network.model.subjectResponse.SubjectModel
import com.example.model.Subject

fun Subject.toSubjectModel() = SubjectModel(
    name = name
)