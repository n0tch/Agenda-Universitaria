package com.core.database.exam

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exams")
data class ExamEntity(
    @PrimaryKey(autoGenerate = true) val examId: Int = 0,
    val name: String?,
    val score: Float?,
    val subjectId: Int?,
    val date: Long?
)
