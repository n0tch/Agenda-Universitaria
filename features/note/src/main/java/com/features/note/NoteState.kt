package com.features.note

sealed class NoteState{
    object NoteIdle: NoteState()
    object NoteLoading: NoteState()
    class NoteSaved(val noteId: String): NoteState()
    class NoteException(val exception: Exception): NoteState()
    class NoteLabels(val labels: List<String>): NoteState()
}
