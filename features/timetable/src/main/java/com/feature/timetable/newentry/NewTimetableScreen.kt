package com.feature.timetable.newentry

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.core.designsystem.components.alert.BasicAlertDialog
import com.core.designsystem.components.combobox.ComboBox
import com.example.model.Subject
import com.example.model.Timetable
import com.feature.timetable.WeekChip
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.Locale
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun NewTimetableScreen(
    subjects: List<Subject> = emptyList(),
    onSaveButtonClicked: (Timetable) -> Unit
) {
    var selectedWeekDays by remember { mutableStateOf(DayOfWeek.MONDAY) }
    var subject by remember { mutableStateOf("") }

    var startTimerPicker by remember { mutableStateOf(false) }
    var endTimerPicker by remember { mutableStateOf(false) }
    var place by remember { mutableStateOf("") }
    var teacher by remember { mutableStateOf("") }

    val timePickerState = rememberTimePickerState()
    val endTimePickerState = rememberTimePickerState()

    Column {
        Text("Selecione o(s) dia(s) da aula")

        ComboBox(
            initialText = "",
            modifier = Modifier.fillMaxWidth(),
            items = subjects.map { it.name },
            onOptionClicked = {
                subject = it
            }
        )

        FlowRow {
            DayOfWeek.values().forEach { weekDay ->
                WeekChip(
                    text = weekDay.getDisplayName(
                        TextStyle.FULL,
                        Locale.getDefault()
                    )
                ) { text, selected ->
                    if (selected) {
                        selectedWeekDays = weekDay
                    } else {
//                        selectedWeekDays.remove(weekDay)
                    }
                }
            }
        }

        Row {
            OutlinedButton(
                onClick = { startTimerPicker = true }
            ) {
                Text("Inicio")
            }
            OutlinedButton(onClick = { endTimerPicker = true }) {
                Text("Fim")
            }
        }

        OutlinedButton(modifier = Modifier.fillMaxWidth(), onClick = {
            onSaveButtonClicked(
                Timetable(
                    weekDay = selectedWeekDays.name,
                    //TODO remove thissss
                    subjectId = subjects.find { it.name == subject }?.id ?: 0,
                    startTime = convertSelectedTime(timePickerState.hour, timePickerState.minute),
                    endTime = convertSelectedTime(endTimePickerState.hour, endTimePickerState.minute),
                )
            )
        }) {
            Text("Save")
        }
    }

    if (startTimerPicker) {
        BasicAlertDialog(onDismiss = { startTimerPicker = false }) {
            TimePicker(state = timePickerState)
        }
    } else if (endTimerPicker) {
        BasicAlertDialog(onDismiss = { endTimerPicker = false }) {
            TimePicker(state = endTimePickerState)
        }
    }
}

fun convertSelectedTime(hour: Int, minute: Int): Long {
    val h = TimeUnit.HOURS.toMillis(hour.toLong())
    val m = TimeUnit.MINUTES.toMillis(minute.toLong())
    return h + m
}

@Preview
@Composable
fun NewTimetableScreenPreview() {
    NewTimetableScreen {}
}