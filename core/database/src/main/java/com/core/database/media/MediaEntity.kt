package com.core.database.media

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_media")
data class MediaEntity(
    @PrimaryKey(autoGenerate = true) val mediaId: Int = 0,
    val noteId: Int,
    val uriPath: String
)
