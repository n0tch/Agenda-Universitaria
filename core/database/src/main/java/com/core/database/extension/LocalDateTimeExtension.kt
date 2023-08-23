package com.core.database.extension

import java.time.LocalDateTime
import java.time.ZoneId

fun LocalDateTime.epochSecond() = atZone(ZoneId.systemDefault()).toInstant().epochSecond