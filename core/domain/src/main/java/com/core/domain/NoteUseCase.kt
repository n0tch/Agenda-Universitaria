package com.core.domain

import android.util.Log
import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.common.Result
import com.core.data.repository.label.LabelRepository
import com.core.data.repository.note.NoteRepository
import com.core.data.repository.notemedia.NoteMediaRepository
import com.core.data.repository.subject.SubjectRepository
import com.example.model.Note
import com.example.model.NoteCompound
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteUseCase @Inject constructor(
    @Dispatcher(AppDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
    private val noteRepository: NoteRepository,
    private val subjectRepository: SubjectRepository,
    private val labelRepository: LabelRepository,
    private val noteMediaRepository: NoteMediaRepository
) {

    fun saveNote(
        note: Note,
        uriPaths: List<String>
    ): Flow<Result<String>> = flow<Result<String>> {
        val savedNote = noteRepository.saveNote(note)
        noteMediaRepository.saveNoteMedia(savedNote.id, uriPaths)
        emit(Result.Success(savedNote.id.toString()))
    }.flowOn(ioDispatcher).catch {
        emit(Result.Error(it as Exception))
    }

    fun fetchNotes(): Flow<Result<List<Note>>> = flow<Result<List<Note>>> {
        val notes = noteRepository.fetchNotes()
        emit(Result.Success(notes))
    }.flowOn(ioDispatcher).catch {
        Log.e("fetchNotes", it.message.toString())
        emit(Result.Error(it as Exception))
    }

    fun searchNotes(query: String): Flow<Result<List<Note>>> = flow<Result<List<Note>>> {
        val notes = noteRepository.searchNote(query)
        emit(Result.Success(notes))
    }.flowOn(ioDispatcher).catch {
        emit(Result.Error(it as Exception))
    }

    fun deleteNote(note: Note): Flow<Result<Boolean>> = flow<Result<Boolean>> {
        val deleted = noteRepository.deleteNote(note)
        if (deleted)
            emit(Result.Success(true))
        else
            throw Exception("")
    }.flowOn(ioDispatcher).catch {
        Log.e("deleteNote", it.message.toString())
        emit(Result.Error(it as Exception))
    }

    fun fetchNoteById(noteId: Int?): Flow<Result<NoteCompound>> = flow {
        if (noteId == null) {
            emit(Result.Error(Exception()))
        } else {
            val noteCompound = noteRepository.fetchNoteById(noteId)
            emit(Result.Success(noteCompound))
        }
    }.flowOn(ioDispatcher).catch {
        emit(Result.Error(it as Exception))
    }

    fun shouldOpenNewNote(noteId: Int?): Boolean = noteId != null
}
