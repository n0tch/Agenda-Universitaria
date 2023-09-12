package com.example.model

data class NoteCompound(
    val note: Note = Note(),
    val labels: List<Label> = emptyList(),
    val subject: Subject = Subject(),
    val uriPaths: List<String> = emptyList()
)
{
    companion object{
        const val EMPTY_NOTE_ID = -1
    }
}