package com.core.data.repository.note

import com.core.data.repository.label.toLabel
import com.core.data.repository.subject.toSubject
import com.core.database.note.NoteEntity
import com.core.database.note.relations.NoteWithLabel
import com.core.database.note.relations.NoteWithLabelWithMediaAndSubject
import com.example.model.Note
import com.example.model.NoteCompound
import com.example.model.NoteWithLabelCompound

internal fun Note.toEntity() = NoteEntity(
    title = title,
    body = body,
    noteSubjectId = subjectId
)

internal fun Note.toEntityWithId() = NoteEntity(
    noteId = id,
    title = title,
    body = body,
    noteSubjectId = subjectId,
    updatedAt = System.currentTimeMillis()
)

internal fun NoteEntity.toNote() = Note(
    id = noteId,
    title = title ?: "",
    body = body ?: "",
    subjectId = noteSubjectId ?: -1,
    createdAt = createdAt,
    updatedAt = updatedAt
)

internal fun NoteWithLabelWithMediaAndSubject.toNoteCompound() = NoteCompound(
    note = note.toNote(),
    labels = labels.map { it.toLabel() },
    subject = subject.toSubject(),
    uriPaths = medias.map { it.uriPath }
)

internal fun NoteWithLabel.toNoteCompound() = NoteWithLabelCompound(
    note = note.toNote(),
    labels = labels.map { it.toLabel() }
)
