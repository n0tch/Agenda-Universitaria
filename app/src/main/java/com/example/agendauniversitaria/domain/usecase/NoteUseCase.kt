package com.example.agendauniversitaria.domain.usecase

import com.example.agendauniversitaria.common.Result
import com.example.agendauniversitaria.data.repository.note.NoteRepository
import com.example.agendauniversitaria.domain.model.Note
import javax.inject.Inject

class NoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {

    suspend fun saveNewNote(note: Note): Result<Boolean> = try {
        val noteSaved = noteRepository.saveNote(note)
        Result.Success(noteSaved)
    }catch (exception: Exception){
        Result.Error(exception)
    }
}