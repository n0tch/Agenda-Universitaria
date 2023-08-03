package com.core.domain

import android.util.Log
import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.common.Result
import com.core.data.repository.note.NoteRepository
import com.core.data.repository.user.UserRepository
import com.example.model.Note
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

class NoteUseCase @Inject constructor(
    @Dispatcher(AppDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
    private val noteRepository: NoteRepository,
    private val userRepository: UserRepository
) {

    fun saveNote(
        title: String,
        description: String,
        label: String,
        subject: String
    ): Flow<Result<String>> = flow {
        val currentUserId = userRepository.fetchCurrentUser().id

        noteRepository
            .saveNote(
                currentUserId,
                Note(title = title, body = description, label = label, subject = subject)
            )
            .flowOn(ioDispatcher)
            .catch { Result.Error(it as Exception) }
            .map { Result.Success(it) }
            .collect { emit(it) }
    }

    fun fetchNotes(): Flow<Result<List<Note>>> = flow {
        val currentUserId = userRepository.fetchCurrentUser().id
        noteRepository.fetchNotes(currentUserId)
            .flowOn(ioDispatcher)
            .catch { Result.Error(it as Exception) }
            .map { Result.Success(it) }
            .collect { emit(it) }
    }

    fun saveNoteLabel(): Flow<Result<Boolean>> = flow {
        noteRepository.saveNoteLabel("").flowOn(ioDispatcher).collect {
            emit(Result.Success(true))
        }
    }

    fun getNoteLabels(): Flow<Result<List<String>>> = flow {
        noteRepository.getNoteLabels().flowOn(ioDispatcher).map { Result.Success(it) }
            .collect { emit(it) }
    }

    fun fetchNoteBySubject(subject: String): Flow<Result<List<Note>>> = flow {
        val currentUserId = userRepository.fetchCurrentUser().id
        noteRepository
            .fetchNotesBySubject(currentUserId, subject)
            .flowOn(ioDispatcher)
            .map { Result.Success(it) }
            .collect { emit(it) }
    }
}
