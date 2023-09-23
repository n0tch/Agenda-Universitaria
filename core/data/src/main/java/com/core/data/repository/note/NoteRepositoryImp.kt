package com.core.data.repository.note

import com.core.database.note.NoteDao
import com.example.model.Note
import com.example.model.NoteCompound
import com.example.model.NotesWithCountCompound
import com.example.model.NoteWithLabelCompound
import javax.inject.Inject

internal class NoteRepositoryImp @Inject constructor(
    private val noteDao: NoteDao,
) : NoteRepository {
    override suspend fun saveNote(note: Note): Note {
        val noteId = noteDao.saveNote(note.toEntity())
        return note.copy(id = noteId.toInt())
    }

    override suspend fun updateNote(note: Note): Note {
        noteDao.updateNote(note.toEntityWithId())
        return note
    }

    override suspend fun fetchNotes(): List<NoteCompound> {
        return noteDao.fetchCompoundNotes().map { it.toNoteCompound() }
    }

    override suspend fun fetchNotes(count: Int): NotesWithCountCompound {
        val noteCount = noteDao.fetchNoteCount()
        val notes = noteDao.fetchCompoundNotes(count).map { it.toNoteCompound() }
        return NotesWithCountCompound(count = noteCount, note = notes)
    }

    override suspend fun fetchNotesBySubject(subjectId: Int): List<NoteWithLabelCompound> {
        return noteDao.fetchNotesBySubjectId(subjectId).map { it.toNoteCompound() }
    }

    override suspend fun fetchNoteById(noteId: Int): NoteCompound {
        return noteDao.fetchNoteWithLabelsAndSubject(noteId).toNoteCompound()
    }

    override suspend fun searchNote(query: String): List<NoteCompound> {
        return noteDao.searchNoteByQuery(query).map { it.toNoteCompound() }
    }

    override suspend fun deleteNote(note: Note): Boolean {
        val deleted = noteDao.deleteNote(note.toEntityWithId())
        return deleted > 0
    }

    override suspend fun fetchNotesByLabelId(labelId: Int): List<NoteCompound> {
        return noteDao.fetchNotesByLabelId(labelId).map { it.toNoteCompound() }
    }

    override suspend fun fetchNotesCount(): Int {
        return noteDao.fetchNoteCount()
    }
}
