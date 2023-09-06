package com.feature.exam.detail

import com.example.model.Subject
import com.example.model.state.BaseState

data class ExamDetailState(
    val subjects: List<Subject> = emptyList(),
    val saved: Boolean = false,
    override val loading: Boolean = false,
    override val error: Exception? = null,
): BaseState
