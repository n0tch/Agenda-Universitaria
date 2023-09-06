package com.core.data.repository.label

import com.example.model.Label
import kotlinx.coroutines.flow.Flow

interface LabelRepository {
    fun saveNoteLabel(label: Label): Flow<Label>

    fun fetchNoteLabels(): Flow<List<Label>>

    suspend fun fetchNoteLabelByNoteId(noteId: Int): List<Label>
}