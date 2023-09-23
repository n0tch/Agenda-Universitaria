package com.example.model.event

private const val ZERO_MINUTES = 0
private const val FIVE_MINUTES_IN_MILLIS = 5000
private const val TEN_MINUTES_IN_MILLIS = 10

enum class NotificationEarlier(val minutes:Int) {
    DISABLED(minutes = ZERO_MINUTES),
    IN_TIME(minutes = ZERO_MINUTES),
    FIVE_MIN_EARLIER(minutes = FIVE_MINUTES_IN_MILLIS),
    TEN_MIN_EARLIER(minutes = TEN_MINUTES_IN_MILLIS),
}
