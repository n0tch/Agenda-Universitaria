package com.example.model

data class NoteCompound(
    val note: Note = Note(),
    val labels: List<Label> = emptyList(),
    val subject: Subject = Subject(),
    val uriPaths: List<String> = emptyList()
)
