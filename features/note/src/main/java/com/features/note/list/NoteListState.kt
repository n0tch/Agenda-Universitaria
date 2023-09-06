package com.features.note.list

import com.example.model.NoteCompound

data class NoteState(
    val isLoading: Boolean = false,
    val notes: List<NoteCompound> = emptyList(),
    val noteDeleted: Int = 0,
    val exception: Exception? = null,
)
