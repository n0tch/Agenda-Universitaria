package com.core.domain

import android.net.Uri
import android.util.Log
import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.common.Result
import com.core.data.repository.note.NoteLabelRepository
import com.core.data.repository.note.NoteRepository
import com.core.data.repository.notemedia.NoteMediaRepository
import com.example.model.Label
import com.example.model.Note
import com.example.model.NoteCompound
import com.example.model.NoteWithLabelCompound
import com.example.model.NotesWithCountCompound
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteUseCase @Inject constructor(
    @Dispatcher(AppDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
    private val noteRepository: NoteRepository,
    private val noteLabelRepository: NoteLabelRepository,
    private val noteMediaRepository: NoteMediaRepository
) {

    suspend fun saveNote(
        note: Note,
        uriPaths: List<Uri?>,
        labels: List<Label>
    ): Result<Boolean> = try {

        val savedNote = noteRepository.saveNote(note)
        noteMediaRepository.saveNoteMedias(savedNote.id, uriPaths)
        noteLabelRepository.saveNoteLabels(savedNote.id, labels)

        Result.Success(true)
    } catch (exception: Exception) {
        Result.Error(exception)
    }

    suspend fun updateNote(
        note: Note,
        uriPaths: List<Uri?>,
        labels: List<Label>
    ): Result<Note> = try {

        val updatedNote = noteRepository.updateNote(note)
        noteMediaRepository.updateNoteMedia(updatedNote.id, uriPaths)
        noteLabelRepository.updateNoteLabels(updatedNote.id, labels)

        Result.Success(updatedNote)
    } catch (exception: Exception) {
        Result.Error(exception)
    }

    suspend fun fetchNotes(): Result<List<NoteCompound>> = try {
        val notes = noteRepository.fetchNotes()
        Result.Success(notes)
    } catch (exception: Exception) {
        Result.Error(exception)
    }

    suspend fun fetchNotesWithCount(count: Int): Result<NotesWithCountCompound> = try {
        val notes = noteRepository.fetchNotes(count)
        Result.Success(notes)
    } catch (exception: Exception) {
        Log.e("fetchNotes with count", exception.message.toString())
        Result.Error(exception)
    }

    fun fetchNoteCount() = flow<Result<Int>> {
        val noteCount = noteRepository.fetchNotesCount()
        emit(Result.Success(noteCount))
    }.flowOn(ioDispatcher).catch {
        Log.e("fetchNotes with count", it.message.toString())
        emit(Result.Error(it as Exception))
    }

    suspend fun searchNotes(query: String): Result<List<NoteCompound>> = try {
        val notes = noteRepository.searchNote(query)
        Result.Success(notes)
    } catch (exception: Exception) {
        Result.Error(exception)
    }

    suspend fun deleteNote(note: Note): Result<Boolean> = try {
        val deleted = noteRepository.deleteNote(note)
        Result.Success(deleted)
    } catch (exception: Exception) {
        Result.Error(exception)
    }

    suspend fun fetchNoteById(noteId: Int): Result<NoteCompound> = try {
        val noteCompound = noteRepository.fetchNoteById(noteId)
        Result.Success(noteCompound)
    } catch (exception: Exception) {
        Result.Error(exception)
    }

    suspend fun fetchNoteBySubjectId(subjectId: Int): Result<List<NoteWithLabelCompound>> = try {
        val notes = noteRepository.fetchNotesBySubject(subjectId)
        Result.Success(notes)
    } catch (exception: Exception) {
        Result.Error(exception)
    }

    suspend fun fetchNotesByLabel(label: Label): Result<List<NoteCompound>> = try {
        val notes = noteRepository.fetchNotesByLabelId(label.id)
        Result.Success(notes)
    } catch (exception: Exception) {
        Result.Error(exception)
    }
}
