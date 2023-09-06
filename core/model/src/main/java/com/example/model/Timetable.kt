package com.example.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Timetable(
    val id: Int = 0,
    val weekDay: String = "",
    val startTime: Long = 0,
    val endTime: Long = 0,
    val subjectId: Int = 0
) : Parcelable