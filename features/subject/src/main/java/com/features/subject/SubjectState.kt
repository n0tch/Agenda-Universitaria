package com.features.subject

import com.example.model.Subject

sealed class SubjectState {
    object Loading: SubjectState()
    object Idle: SubjectState()
    class SavedSuccess(val subjectId: String): SubjectState()
    class FetchSuccess(val items: List<Subject>): SubjectState()
    class Error(val exception: Exception): SubjectState()
}
