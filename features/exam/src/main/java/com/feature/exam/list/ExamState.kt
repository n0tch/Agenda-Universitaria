package com.feature.exam.list

import com.example.model.event.Exam
import com.example.model.state.BaseState

sealed class ExamState{
    object Loading: ExamState()
    object Idle: ExamState()
    class ExamList(val exams: List<Exam>): ExamState()
    class Error(val exception: Exception): ExamState()
}

data class ExamState1(
    override val loading: Boolean = false,
    override val error: Exception? = null,
    val exams: List<Exam> = emptyList()
): BaseState