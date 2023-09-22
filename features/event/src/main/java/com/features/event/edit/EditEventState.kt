package com.features.event.edit

import com.example.model.Label
import com.example.model.Subject

data class EditEventState(
    val subjects: List<Subject> = emptyList(),
    val labels: List<Label> = emptyList(),
    val saved: Boolean = false,
    val isLoading: Boolean = false
)

sealed class EditEventSideEffect{
    object OnBack: EditEventSideEffect()

}