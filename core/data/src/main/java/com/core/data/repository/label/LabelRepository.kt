package com.core.data.repository.label

import com.example.model.Label
import com.example.model.Note
import com.example.model.NoteCompound
import kotlinx.coroutines.flow.Flow

interface LabelRepository {
    fun saveNoteLabel(label: Label): Flow<Label>

    fun fetchNoteLabels(): Flow<List<Label>>

    suspend fun fetchNoteLabelByNoteId(noteId: Int): List<Label>
}
