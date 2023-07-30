package com.core.data.extension

import com.core.network.model.noteResponse.NoteResponse
import com.example.model.Note

fun NoteResponse.toNote() = Note(
    id = id ?: "",
    title = title ?: "",
    body = description ?: "",
    label = label ?: "",
    subject = subject ?: ""
)