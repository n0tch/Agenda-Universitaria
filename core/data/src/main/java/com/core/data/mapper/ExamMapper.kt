package com.core.data.mapper

import com.core.network.model.examResponse.ExamResponse
import com.example.model.event.Exam

class ExamMapper {
    fun mapToDomain(examResponse: ExamResponse?): Exam = Exam(
        name = examResponse?.name ?: "",
        date = examResponse?.date ?: 0L,
        subjectId = examResponse?.subjectId ?: "",
        relatedNotes = examResponse?.relatedNotes?.map { it } ?: emptyList(),
        score = examResponse?.score ?: 0F,
        id = examResponse?.id ?: ""
    )

    fun mapListToDomain(examListResponse: List<ExamResponse?>?) = examListResponse?.mapNotNull {
        mapToDomain(it)
    } ?: emptyList()

    fun mapToResponse(exam: Exam): ExamResponse = ExamResponse(
        id = exam.id,
        name = exam.name,
        subjectId = exam.subjectId,
        relatedNotes = exam.relatedNotes,
        score = exam.score,
        date = exam.date
    )
}