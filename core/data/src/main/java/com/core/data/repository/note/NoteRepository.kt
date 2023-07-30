package com.core.data.repository.note

import com.example.model.Note
import com.example.model.NoteLabel
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun saveNote(userId: String, note: Note): Flow<String>

    fun fetchNotes(userId: String): Flow<List<Note>>

    fun saveNoteLabel(noteLabel: String): Flow<String>

    fun getNoteLabels(): Flow<List<String>>

    fun fetchNotesBySubject(userId: String, subject: String): Flow<List<Note>>
}