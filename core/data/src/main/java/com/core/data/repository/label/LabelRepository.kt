package com.core.data.repository.label

import com.example.model.Label

interface LabelRepository {
    suspend fun saveNoteLabel(label: Label): Label

    suspend fun fetchNoteLabels(): List<Label>

    suspend fun fetchNoteLabelByNoteId(noteId: Int): List<Label>
}
