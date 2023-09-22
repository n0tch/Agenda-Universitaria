package com.features.note.edit

import com.example.model.Label
import com.example.model.NoteCompound
import com.example.model.Subject

data class EditNoteState(
    val isLoading: Boolean = false,
    val labels: List<Label> = emptyList(),
    val subjects: List<Subject> = emptyList(),
    val noteCompound: NoteCompound = NoteCompound(),
    val noteSaved: Boolean = false,
    val labelSaved: Boolean = false,
    val exception: Exception? = null
)

sealed class EditNoteSideEffect{
    data class Toast(val message: String): EditNoteSideEffect()
}

data class LabelWithSubjectState(
    val labels: List<Label> = emptyList(),
    val subjects: List<Subject> = emptyList()
)
