package com.core.database.note.relations

import androidx.room.Entity

@Entity(primaryKeys = ["noteId", "mediaId"])
data class NoteMediaCrossRef(
    val noteId: Int,
    val mediaId: Int
)
