package com.core.database.databaseModel

data class TimetableDatabaseModel(
    val id: String,
    val weekDays: String,
    val startTime: Long,
    val endTime: Long,
    val subjectId: String,
)