package com.features.subject.list

import com.example.model.Subject

data class SubjectState(
    val isLoading: Boolean = false,
    val subjectId: Int = 0,
    val subjects: List<Subject> = emptyList(),
    val exception: Exception? = null
)