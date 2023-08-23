package com.core.data.mapper

import com.core.database.databaseModel.ExamDataModel
import com.example.model.event.Exam
import java.time.LocalDateTime

class ExamMapper {

    fun mapToDatabaseModel(exam: Exam) = ExamDataModel(
        id = exam.id,
        name = exam.name,
        score = exam.score,
        color = exam.color,
        subjectId = exam.subjectId,
        subjectNotes = exam.relatedNotes,
        date = exam.date
    )

    fun mapToDomain(exam: ExamDataModel?) = Exam(
        id = exam?.id ?: "",
        name = exam?.name ?: "",
        score = exam?.score ?: 0F,
        color = exam?.color ?: 1,
        subjectId = exam?.subjectId ?: "",
        relatedNotes = exam?.subjectNotes ?: emptyList(),
        date = exam?.date ?: LocalDateTime.now()
    )
}