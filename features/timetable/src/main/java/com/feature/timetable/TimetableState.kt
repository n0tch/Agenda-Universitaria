package com.feature.timetable

import com.example.model.TimetableCompound
import java.time.DayOfWeek

data class TimetableState(
    val isLoading: Boolean = false,
    val exception: Exception? = null,
    val items: List<TimetableCompound> = listOf()
)

data class TimetableMap(
    val items: Map<DayOfWeek, List<TimetableCompound>> = mapOf()
)