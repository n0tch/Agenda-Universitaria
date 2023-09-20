package com.core.data.repository.label

import com.example.model.Label
import kotlinx.coroutines.flow.Flow

interface LabelRepository {
    fun saveNoteLabel(label: Label): Flow<Label>

    suspend fun fetchNoteLabels(): List<Label>

    suspend fun fetchNoteLabelByNoteId(noteId: Int): List<Label>
}
