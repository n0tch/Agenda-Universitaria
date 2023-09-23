package com.core.data.repository.note

import com.example.model.Label

interface NoteLabelRepository {

    suspend fun saveNoteLabels(noteId: Int, labelId: List<Label>)

    suspend fun updateNoteLabels(noteId: Int, labelId: List<Label>)
}
