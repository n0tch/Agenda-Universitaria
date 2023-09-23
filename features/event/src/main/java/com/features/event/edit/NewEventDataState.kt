package com.features.event.edit

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.model.Label
import com.example.model.Subject
import com.example.model.event.Event
import com.example.model.event.EventCompound
import com.example.model.event.EventGroup
import com.example.model.event.EventNotification
import com.example.model.event.EventScore
import com.example.model.event.EventToSave
import com.example.model.event.NotificationPeriod
import java.util.Calendar

data class NewEventDataState(
    var name: MutableState<String> = mutableStateOf(""),
    var label: MutableState<Label> = mutableStateOf(Label()),
    var subjectId: MutableState<Int> = mutableStateOf(0),
    var hasScore: MutableState<Boolean> = mutableStateOf(true),
    var score: MutableState<String> = mutableStateOf(""),
    var hasNotification: MutableState<Boolean> = mutableStateOf(true),
    var notificationPeriod: MutableState<String> = mutableStateOf(""),
    var date: MutableState<Long> = mutableStateOf(0L),
    var hour: MutableState<Int> = mutableStateOf(0),
    var minute: MutableState<Int> = mutableStateOf(0),
    var color: MutableState<Color> = mutableStateOf(Color.Unspecified)
)

fun NewEventDataState.toEventData() = EventToSave(
    event = Event(
        name = name.value,
        date = toCalendar(date.value, hour.value, minute.value),
        color = color.value.toArgb().toString()
    ),
    label = label.value,
    subjectId = subjectId.value,
    hasNotification = hasNotification.value,
    hasScore = hasScore.value,
    eventScore = if (hasScore.value) EventScore(score = score.value.toFloatOrNull()) else null,
    eventNotification = if (hasNotification.value) EventNotification(
        notifyAt = toCalendar(date.value, hour.value, minute.value),
        notificationPeriod = NotificationPeriod.valueOf(notificationPeriod.value)
    ) else null
)

fun toCalendar(date: Long, hour: Int, minute: Int): Long = Calendar.getInstance().apply {
    timeInMillis = date
    set(Calendar.HOUR_OF_DAY, hour)
    set(Calendar.MINUTE, minute)
}.timeInMillis