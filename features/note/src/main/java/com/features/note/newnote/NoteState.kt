package com.features.note.newnote

import com.example.model.Label
import com.example.model.NoteCompound
import com.example.model.Subject

data class NoteState(
    val isLoading: Boolean = false,
    val noteCompound: NoteCompound = NoteCompound(),
    val noteSaved: Boolean = false,
    val exception: Exception? = null
)

data class LabelState(
    val labels: List<Label> = emptyList(),
)

data class SubjectsState(
    val subjects: List<Subject> = emptyList()
)
