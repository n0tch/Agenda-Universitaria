package com.core.data.repository.note

import com.core.data.extension.toNote
import com.core.data.extension.toNoteLabel
import com.core.data.extension.toNoteResponse
import com.core.network.note.NoteDataProvider
import com.example.model.Note
import com.example.model.NoteLabel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteRepositoryImp @Inject constructor(
    private val noteDataProvider: NoteDataProvider,

    ) : NoteRepository {
    override fun saveNote(userId: String, note: Note): Flow<String> = flow {
        noteDataProvider.saveNote(userId, note.toNoteResponse()).collect {
            emit(it)
        }
    }

    override fun fetchNotes(userId: String): Flow<List<Note>> = flow {
        noteDataProvider.fetchNotes(userId)
            .map { noteResponseList ->
                noteResponseList.mapNotNull { it?.toNote() }
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
}
