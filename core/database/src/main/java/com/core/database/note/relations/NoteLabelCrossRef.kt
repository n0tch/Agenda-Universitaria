package com.core.database.note.relations

import androidx.room.Entity

@Entity(primaryKeys = ["noteId", "labelId"])
data class NoteLabelCrossRef(
    val noteId: Int,
    val labelId: Int
)
