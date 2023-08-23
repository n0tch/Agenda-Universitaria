package com.core.database.databaseModel

import java.time.LocalDateTime

data class ExamDataModel(
    val id: String,
    val name: String,
    val score: Float,
    val color: Int,
    val subjectId: String,
    val subjectNotes: List<String>,
    val date: LocalDateTime
)
