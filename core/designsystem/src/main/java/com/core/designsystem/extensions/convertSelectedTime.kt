package com.core.designsystem.extensions

import java.util.concurrent.TimeUnit

fun convertSelectedTime(hour: Int, minute: Int): Long {
    val h = TimeUnit.HOURS.toMillis(hour.toLong())
    val m = TimeUnit.MINUTES.toMillis(minute.toLong())
    return h + m
}