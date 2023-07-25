package com.example.agendauniversitaria.data.repository.note

import com.example.agendauniversitaria.domain.model.Note

interface NoteRepository {

    suspend fun saveNote(note: Note): Boolean

}
