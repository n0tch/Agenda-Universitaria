package com.core.data.repository.note

import com.core.data.mapper.NoteMapper
import com.core.network.note.NoteDataProvider
import com.example.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject

class NoteRepositoryImp @Inject constructor(
    private val noteDataProvider: NoteDataProvider,
    private val noteMapper: NoteMapper
) : NoteRepository {
    override fun saveNote(userId: String, note: Note): Flow<String> = flow {
        noteDataProvider
            .saveNote(userId, noteMapper.mapToResponse(note))
            .mapNotNull { it.id }
            .collect {
                emit(it)
            }
    }

    override fun fetchNotes(userId: String): Flow<List<Note>> = flow {
        noteDataProvider.fetchNotes(userId)
            .map { noteResponseList ->
                noteResponseList.mapNotNull { noteMapper.mapToDomain(it) }
            }.collect {
                emit(it)
            }
    }

    override fun saveNoteLabel(noteLabel: String): Flow<String> = flow {
        noteDataProvider.saveNoteLabel("").collect { emit(it) }
    }

    override fun getNoteLabels(): Flow<List<String>> = flow {
        noteDataProvider
            .getNoteLabels()
            .map { labelList ->
                labelList.mapNotNull { it }
            }.collect { emit(it) }
    }

    override fun fetchNotesBySubject(userId: String, subject: String): Flow<List<Note>> = flow {
        noteDataProvider
            .fetchNotesBySubject(userId, subject)
            .map { notesResponse -> noteMapper.mapListToDomain(notesResponse) }
            .collect { emit(it) }
    }

    override fun fetchNoteById(userId: String, noteId: String): Flow<Note> = flow {
        noteDataProvider.fetchNoteById(userId, noteId).mapNotNull { noteMapper.mapToDomain(it) }
            .collect { emit(it) }
    }
}
