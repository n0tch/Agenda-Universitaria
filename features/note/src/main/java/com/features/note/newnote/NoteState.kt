package com.features.note.newnote

import com.example.model.Subject

sealed class NoteState {
    object NoteIdle : NoteState()
    object NoteLoading : NoteState()
    class NoteSaved(val noteId: String) : NoteState()
    class NoteException(val exception: Exception) : NoteState()
    class NoteLabels(val labels: List<String>) : NoteState()
    class SubjectList(val items: List<Subject>) : NoteState()
}
