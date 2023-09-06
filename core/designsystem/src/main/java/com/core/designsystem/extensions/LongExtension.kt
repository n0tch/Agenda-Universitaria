package com.core.designsystem.extensions

import java.text.SimpleDateFormat
import java.util.Locale

fun Long.toMinuteAndSecond(): String {
    val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    return dateFormat.format(this)
}

fun Long.toDayMonthYear(): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return dateFormat.format(this)
}