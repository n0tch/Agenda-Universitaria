package com.features.note.list

import com.example.model.Note

sealed class NoteListState {
    object Idle: NoteListState()
    object Loading: NoteListState()
    class NoteList(val notes: List<Note>): NoteListState()
    class Error(val exception: Exception): NoteListState()
}
