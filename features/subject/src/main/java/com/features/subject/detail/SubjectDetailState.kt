package com.features.subject.detail

import com.example.model.Note
import java.lang.Exception

sealed class SubjectDetailState{
    object Loading: SubjectDetailState()
    object Idle: SubjectDetailState()
    class Error(val exception: Exception): SubjectDetailState()
    class NoteList(val items: List<Note>): SubjectDetailState()
}
