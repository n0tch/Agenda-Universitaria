package com.core.data.repository.note

import com.example.model.Note
import com.example.model.NoteCompound
import com.example.model.NotesWithCountCompound
import com.example.model.NoteWithLabelCompound

interface NoteRepository {

    suspend fun saveNote(note: Note): Note

    suspend fun updateNote(note: Note): Note

    suspend fun fetchNotes(): List<NoteCompound>

    suspend fun fetchNotes(count: Int): NotesWithCountCompound

    suspend fun fetchNotesBySubject(subjectId: Int): List<NoteWithLabelCompound>

    suspend fun fetchNoteById(noteId: Int): NoteCompound

    suspend fun searchNote(query: String): List<NoteCompound>

    suspend fun deleteNote(note: Note): Boolean

    suspend fun fetchNotesByLabelId(labelId: Int): List<NoteCompound>

    suspend fun fetchNotesCount(): Int
}
