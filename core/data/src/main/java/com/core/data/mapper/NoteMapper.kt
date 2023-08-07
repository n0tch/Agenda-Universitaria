package com.core.data.mapper

import com.core.network.model.noteResponse.NoteResponse
import com.example.model.Note

class NoteMapper {

    fun mapToDomain(noteResponse: NoteResponse?) = Note(
        id = noteResponse?.id ?: "",
        title = noteResponse?.title ?: "",
        body = noteResponse?.description ?: "",
        label = noteResponse?.label ?: "",
        subject = noteResponse?.subject ?: ""
    )

    fun mapListToDomain(noteListResponse: List<NoteResponse?>?) = noteListResponse?.mapNotNull {
        mapToDomain(it)
    } ?: emptyList()

    fun mapToResponse(note: Note) = NoteResponse(
        id = note.id,
        title = note.title,
        description = note.body,
        label = note.label,
        subject = note.subject
    )
}
