package com.features.subject.list

import com.example.model.Subject

data class SubjectState(
    val isLoading: Boolean = false,
    val subjectId: Int = 0,
    val subjects: List<Subject> = emptyList(),
    val exception: Exception? = null
)

sealed class SubjectListSideEffect{
    data class Toast(val message: String): SubjectListSideEffect()
    data class NavigateToDetail(val subject: Subject): SubjectListSideEffect()
    object NavigateToNewSubject: SubjectListSideEffect()
    object OnBack: SubjectListSideEffect()
}