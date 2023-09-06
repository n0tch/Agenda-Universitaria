package com.core.data.repository.exam

import com.core.database.exam.ExamEntity
import com.example.model.event.Exam

internal fun Exam.toEntity() = ExamEntity(
    score = score,
    subjectId = subjectId,
    name = name,
    date = date
)

internal fun ExamEntity.toExam() = Exam(
    id = examId,
    relatedNotes = emptyList(),
    score = score ?: 0F,
    subjectId = subjectId ?: -1,
    date = date ?: 0L,
    name = name ?: ""
)