package com.features.note.list

import com.example.model.Note

data class NoteState(
    val isLoading: Boolean = false,
    val notes: List<Note> = emptyList(),
    val noteDeleted: Int = 0,
    val exception: Exception? = null,
)
