package com.example.agendauniversitaria.network.note

import com.example.agendauniversitaria.domain.model.Note

interface NoteDataSource {

    suspend fun saveNote(note: Note)

}