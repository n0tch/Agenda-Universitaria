package com.core.data.repository.note

import com.example.model.Note
import com.example.model.NoteCompound

interface NoteRepository {

    suspend fun saveNote(note: Note): Note

    suspend fun fetchNotes(): List<Note>

    suspend fun fetchNotesBySubject(subjectId: Int): List<Note>

    suspend fun fetchNoteById(noteId: Int): NoteCompound

    suspend fun searchNote(query: String): List<Note>

    suspend fun deleteNote(note: Note): Boolean

    suspend fun saveNoteImagePaths(noteId: Int, images: List<String>)
}