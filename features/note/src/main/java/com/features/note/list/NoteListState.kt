package com.features.note.list

import com.example.model.Label
import com.example.model.NoteCompound

data class NoteListState(
    val isLoading: Boolean = false,
    val notes: List<NoteCompound> = emptyList(),
    val labels: List<Label> = emptyList(),
    val noteDeleted: Boolean = false,
    val exception: Exception? = null,
)

sealed class NoteListSideEffect{
    data class Toast(val message: String): NoteListSideEffect()
    object ShowFilter: NoteListSideEffect()
}