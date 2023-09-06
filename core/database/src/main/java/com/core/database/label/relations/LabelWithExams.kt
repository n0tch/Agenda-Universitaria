package com.core.database.label.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.core.database.exam.ExamEntity
import com.core.database.exam.relations.label.ExamLabelCrossRef
import com.core.database.label.LabelEntity

data class LabelWithExams(
    @Embedded val label: LabelEntity,

    @Relation(
        parentColumn = "labelId",
        entityColumn = "examId",
        associateBy = Junction(ExamLabelCrossRef::class)
    )
    val exams: List<ExamEntity>
)
