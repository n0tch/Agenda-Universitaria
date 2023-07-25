package com.example.agendauniversitaria.data.repository.note

import com.example.agendauniversitaria.data.repository.note.NoteRepository
import com.example.agendauniversitaria.domain.model.Note
import com.example.agendauniversitaria.network.note.NoteDataSource
import javax.inject.Inject

class NoteRepositoryImp @Inject constructor(
    private val noteDataSource: NoteDataSource
): NoteRepository {

    override suspend fun saveNote(note: Note): Boolean {
        noteDataSource.saveNote(note)
        return true
    }
}