package com.example.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TimetableEntry(
    val weekDays: List<String>,
    val startTime: String,
    val endTime: String,
    val subjectId: String
) : Parcelable

fun List<TimetableEntry>.getEntryByWeekDayName(weekDayName: String) =
    filter { it.weekDays.contains(weekDayName) }.sortedBy { it.startTime }