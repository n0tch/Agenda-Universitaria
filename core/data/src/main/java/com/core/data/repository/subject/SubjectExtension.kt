package com.core.data.repository.subject

import com.core.data.repository.exam.toExam
import com.core.data.repository.note.toNote
import com.core.database.subject.SubjectEntity
import com.core.database.subject.relations.SubjectWithNotesWithExams
import com.example.model.Subject
import com.example.model.SubjectCompound

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

internal fun SubjectWithNotesWithExams.toSubjectCompound() = SubjectCompound(
    subject = subject.toSubject(),
    notes = notes.map { it.toNote() },
    exams = exams.map { it.toExam() }
)