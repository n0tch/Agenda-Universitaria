package com.core.database.exam.relations.label

import androidx.room.Entity

@Entity(primaryKeys = ["examId", "labelId"])
data class ExamLabelCrossRef(
    val examId: Int,
    val labelId: Int
)
