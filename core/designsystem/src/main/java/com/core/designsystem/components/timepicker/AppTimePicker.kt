package com.core.designsystem.components.timepicker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTimePicker(
    onTimeSelected: (TimePickerState, TimePickerState) -> Unit = { _, _ ->}
) {

    val startTimePicker = rememberTimePickerState()
    val endTimePicker = rememberTimePickerState()

    var showTimePicker by remember { mutableStateOf(0) }

    val startText: () -> String = {
        "${startTimePicker.hour}:${startTimePicker.minute}"
    }

    val endText: () -> String = {
        "${endTimePicker.hour}:${endTimePicker.minute}"
    }

    Row{
        OutlinedButton(onClick = { showTimePicker = 1 }) {
            Text("Start")
        }

        OutlinedButton(onClick = { showTimePicker = 2 }) {
            Text("End")
        }

        Column {
            Text(startText())
            Text(endText())
        }
    }

    if (showTimePicker == 1 || showTimePicker == 2) {
        AlertDialog(
            onDismissRequest = {
                showTimePicker = 0
                onTimeSelected(startTimePicker, endTimePicker)
            },
            content = {
                when(showTimePicker){
                    1 -> TimePicker(state = startTimePicker)
                    2 -> TimePicker(state = endTimePicker)
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AppTimePickerPreview() {
    AppTimePicker(){ _, _ -> }
}