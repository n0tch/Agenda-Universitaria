package com.features.note.edit

import com.example.model.Label
import com.example.model.NoteCompound
import com.example.model.Subject

data class NoteState(
    val isLoading: Boolean = false,
    val noteCompound: NoteCompound = NoteCompound(),
    val noteSaved: Boolean = false,
    val exception: Exception? = null
)

data class LabelWithSubjectState(
    val labels: List<Label> = emptyList(),
    val subjects: List<Subject> = emptyList()
)
