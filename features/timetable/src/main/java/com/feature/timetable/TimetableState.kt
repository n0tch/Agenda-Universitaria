package com.feature.timetable

import com.example.model.Timetable

data class TimetableState(
    val isLoading: Boolean = false,
    val exception: Exception? = null,
    val items: List<Timetable> = listOf()
)
