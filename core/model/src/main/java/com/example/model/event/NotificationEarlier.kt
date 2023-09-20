package com.example.model.event

enum class NotificationEarlier(val min:Int) {
    DISABLED(0),
    IN_TIME(0),
    FIVE_MIN_EARLIER(5000),
    TEN_MIN_EARLIER(10000),
    //TODO: CUSTOM
}