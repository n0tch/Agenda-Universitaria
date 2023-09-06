package com.core.database.exam.relations.label

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.core.database.exam.ExamEntity
import com.core.database.label.LabelEntity

data class ExamWithLabel(
    @Embedded val exam: ExamEntity,

    @Relation(
        parentColumn = "examId",
        entityColumn = "LabelId",
        associateBy = Junction(ExamLabelCrossRef::class)
    )
    val labels: List<LabelEntity> = emptyList()
)
