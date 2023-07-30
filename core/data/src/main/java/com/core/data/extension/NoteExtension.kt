package com.core.data.extension

import com.core.network.model.noteResponse.NoteResponse
import com.example.model.Note

fun Note.toNoteResponse() = NoteResponse(
    id = id,
    title = title,
    description = body,
    label = label,
    subject = subject
)
