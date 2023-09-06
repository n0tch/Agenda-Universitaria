package com.core.database.note.relations

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["noteId", "mediaId"])
data class NoteMediaCrossRef(
    @ColumnInfo(index = true)
    val noteId: Int,
    @ColumnInfo(index = true)
    val mediaId: Int
)
