package com.core.data.repository.note

import android.util.Log
import com.core.database.note.NoteDao
import com.example.model.Note
import com.example.model.NoteCompound
import javax.inject.Inject

internal class NoteRepositoryImp @Inject constructor(
    private val noteDao: NoteDao,
) : NoteRepository {
    override suspend fun saveNote(note: Note): Note {
        val noteId = noteDao.saveNote(note.toEntity())
        return note.copy(id = noteId.toInt())
    }

    override suspend fun fetchNotes(): List<Note> {
        return noteDao
            .fetchCompoundNotes()
            .map { it.toNote() }
    }

    override suspend fun fetchNotesBySubject(subjectId: Int): List<Note> {
        return emptyList()//noteDao.fetchNotesBySubjectId(subjectId).map { it.toNote() }
    }

    override suspend fun fetchNoteById(noteId: Int): NoteCompound {
        val note = noteDao.fetchNoteWithLabelsAndSubject(noteId).toNoteCompound()
        Log.e("notes", note.toString())
        return noteDao.fetchNoteWithLabelsAndSubject(noteId).toNoteCompound()
    }

    override suspend fun searchNote(query: String): List<Note> {
        return noteDao.searchNoteByQuery(query).map { it.toNote() }
    }

    override suspend fun deleteNote(note: Note): Boolean {
        val deleted = noteDao.deleteNote(note.toEntityWithId())
        return deleted > 0
    }

    override suspend fun saveNoteImagePaths(noteId: Int, images: List<String>) {
        TODO("Not yet implemented")
    }
}
