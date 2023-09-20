package com.core.domain

import android.net.Uri
import android.util.Log
import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.common.Result
import com.core.data.repository.label.LabelRepository
import com.core.data.repository.note.NoteLabelRepository
import com.core.data.repository.note.NoteRepository
import com.core.data.repository.notemedia.NoteMediaRepository
import com.core.data.repository.subject.SubjectRepository
import com.example.model.Label
import com.example.model.Note
import com.example.model.NoteCompound
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteUseCase @Inject constructor(
    @Dispatcher(AppDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
    private val noteRepository: NoteRepository,
    private val subjectRepository: SubjectRepository,
    private val labelRepository: LabelRepository,
    private val noteLabelRepository: NoteLabelRepository,
    private val noteMediaRepository: NoteMediaRepository
) {

    fun saveNote(
        note: Note,
        uriPaths: List<Uri?>,
        labels: List<Label>
    ) = flow<Result<String>> {

        val savedNote = noteRepository.saveNote(note)
        noteMediaRepository.saveNoteMedias(savedNote.id, uriPaths)
        noteLabelRepository.saveNoteLabels(savedNote.id, labels)

        emit(Result.Success(savedNote.id.toString()))
    }.flowOn(ioDispatcher).catch {
        Log.e("saveNote", it.message.toString())
        emit(Result.Error(it as Exception))
    }

    fun updateNote(
        note: Note,
        uriPaths: List<Uri?>,
        labels: List<Label>
    ) = flow<Result<Note>> {

        val updatedNote = noteRepository.updateNote(note)
        noteMediaRepository.updateNoteMedia(updatedNote.id, uriPaths)
        noteLabelRepository.updateNoteLabels(updatedNote.id, labels)

        emit(Result.Success(updatedNote))
    }.flowOn(ioDispatcher).catch {
        Log.e("updateNote", it.message.toString())
        emit(Result.Error(it as Exception))
    }

    suspend fun fetchNotes(): Result<List<NoteCompound>> = try {
        val notes = noteRepository.fetchNotes()
        Result.Success(notes)
    }catch (exception: Exception){
        Result.Error(exception)
    }

    fun fetchNotes(count: Int) = flow <Result<List<NoteCompound>>> {
        val notes = noteRepository.fetchNotes(count)
        emit(Result.Success(notes))
    }.flowOn(ioDispatcher).catch {
        Log.e("fetchNotes with count", it.message.toString())
        emit(Result.Error(it as Exception))
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
    }catch (exception: Exception){
        Result.Error(exception)
    }

    suspend fun deleteNote(note: Note): Result<Boolean> = try {
        val deleted = noteRepository.deleteNote(note)
        if (deleted)
            Result.Success(true)
        else
            throw Exception("")
    }catch (exception: Exception){
        Result.Error(exception)
    }

    fun fetchNoteById(noteId: Int) = flow<Result<NoteCompound>> {
        val noteCompound = if (noteId == NoteCompound.EMPTY_NOTE_ID)
            NoteCompound()
        else
            noteRepository.fetchNoteById(noteId)

        emit(Result.Success(noteCompound))
    }.flowOn(ioDispatcher).catch {
        emit(Result.Error(it as Exception))
    }

    suspend fun fetchNotesByLabel(label: Label): Result<List<NoteCompound>> = try {
        val notes = noteRepository.fetchNotesByLabelId(label.id)
        Result.Success(notes)
    }catch (exception: Exception){
        Result.Error(exception)
    }
}
