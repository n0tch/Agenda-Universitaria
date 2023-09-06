package com.core.database.note.relations

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["noteId", "labelId"])
data class NoteLabelCrossRef(
    @ColumnInfo(index = true)
    val noteId: Int,
    @ColumnInfo(index = true)
    val labelId: Int
)
