package com.feature.timetable

import com.example.model.TimetableCompound

data class TimetableState(
    val isLoading: Boolean = false,
    val exception: Exception? = null,
    val items: List<TimetableCompound> = listOf()
)
