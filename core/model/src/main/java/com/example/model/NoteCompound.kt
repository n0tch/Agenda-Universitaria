package com.example.model

data class NoteCompound(
    val note: Note = Note(),
    val labels: List<Label> = emptyList(),
    val subject: Subject = Subject(),
    val uriPaths: List<String> = emptyList()
)

data class NotesWithCountCompound(
    val note: List<NoteCompound> = emptyList(),
    val count: Int = 0
)
