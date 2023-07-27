package com.core.data.extension

import com.core.network.model.noteResponse.NoteResponse
import com.example.model.Note

fun NoteResponse.toNote() = Note(
    title = title ?: "",
    body = description ?: ""
)