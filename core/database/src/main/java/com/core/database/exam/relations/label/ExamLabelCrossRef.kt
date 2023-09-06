package com.core.database.exam.relations.label

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["examId", "labelId"])
data class ExamLabelCrossRef(
    @ColumnInfo(index = true)
    val examId: Int,
    @ColumnInfo(index = true)
    val labelId: Int
)
