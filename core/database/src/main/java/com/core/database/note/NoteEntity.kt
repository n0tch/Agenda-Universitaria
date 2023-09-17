package com.core.database.note

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val noteId: Int = 0,
    val title: String?,
    val body: String?,
    val noteSubjectId: Int?,
    val createdAt: Long? = System.currentTimeMillis(),
    val updatedAt: Long? = null
)
