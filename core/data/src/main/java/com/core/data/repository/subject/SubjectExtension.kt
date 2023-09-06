package com.core.data.repository.subject

import com.core.database.subject.SubjectEntity
import com.example.model.Subject

internal fun Subject.toEntity() = SubjectEntity(
    name = name,
    place = place,
    teacher = teacher
)

internal fun SubjectEntity.toSubject() = Subject(
    id = subjectId,
    name = name ?: "",
    teacher = teacher ?: "",
    place = place ?: ""
)
