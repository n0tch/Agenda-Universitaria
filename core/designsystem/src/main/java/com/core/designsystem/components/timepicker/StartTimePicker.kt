package com.core.designsystem.components.timepicker

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartTimePicker(
    onTimeSelected: (Long) -> Unit = {  }
) {

    val startTimePicker = rememberTimePickerState()

    var showTimePicker by remember { mutableStateOf(false) }

    val startText: () -> String = {
        "${startTimePicker.hour}:${startTimePicker.minute}"
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        OutlinedButton(onClick = { showTimePicker = true }) {
            Text("Start")
        }

        Column {
            Text(startText())
        }
    }

    if (showTimePicker) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .border(1.dp, MaterialTheme.colorScheme.onBackground)
                .padding(4.dp)
        ) {
            AlertDialog(
                onDismissRequest = {
                    showTimePicker = false
                    val calendar = Calendar.getInstance()
                    calendar.set(Calendar.HOUR_OF_DAY, startTimePicker.hour)
                    calendar.set(Calendar.MINUTE, startTimePicker.minute)
                    onTimeSelected(calendar.timeInMillis)
                },
                content = {
                    TimePicker(state = startTimePicker)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun StartTimePickerPreview() {
    StartTimePicker() {  }
}