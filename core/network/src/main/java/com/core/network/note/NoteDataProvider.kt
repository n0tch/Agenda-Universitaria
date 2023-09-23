package com.core.network.note

import com.core.network.model.noteResponse.NoteResponse
import kotlinx.coroutines.flow.Flow

interface NoteDataProvider {

    fun saveNote(userId: String, note: NoteResponse): Flow<NoteResponse>

    fun fetchNotes(userId: String): Flow<List<NoteResponse?>>

    fun saveNoteLabel(noteLabel: String): Flow<String>

    fun getNoteLabels(): Flow<List<String?>>

    fun fetchNotesBySubject(userId: String, subject: String): Flow<List<NoteResponse?>>

    fun fetchNoteById(userId: String, noteId: String): Flow<NoteResponse?>
}
