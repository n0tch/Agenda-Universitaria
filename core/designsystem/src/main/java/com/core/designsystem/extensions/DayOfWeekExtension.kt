package com.core.designsystem.extensions

import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.Locale

fun DayOfWeek.asLocalizedDate(): String = getDisplayName(TextStyle.FULL, Locale.getDefault())