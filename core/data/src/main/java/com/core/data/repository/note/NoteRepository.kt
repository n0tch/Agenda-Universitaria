package com.core.data.repository.note

import com.example.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun saveNote(userId: String, note: Note): Flow<String>

    fun fetchNotes(userId: String): Flow<List<Note>>
}