package com.example.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Timetable(
    val id: Int = 0,
    val weekDay: String,
    val startTime: Long,
    val endTime: Long,
    val subject: Subject?
) : Parcelable