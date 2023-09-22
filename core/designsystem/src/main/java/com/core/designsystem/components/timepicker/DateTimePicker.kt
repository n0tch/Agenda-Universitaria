package com.core.designsystem.components.timepicker

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTimePicker(
    onDateSelected: (Long?) -> Unit,
    onTimeSelected: (hour: Int, minute: Int) -> Unit
) {
    val datePickerState = rememberDatePickerState(Calendar.getInstance().timeInMillis)
    val timePickerState = rememberTimePickerState()

    var showDateDialog by remember { mutableStateOf(false) }
    var showTimeDialog by remember { mutableStateOf(false) }

    Button(onClick = { showDateDialog = true }) {
        Text("Selecionar data")
    }

    Button(onClick = { showTimeDialog = true }) {
        Text("Selecionar a hora")
    }

    if (showDateDialog) {
        DatePickerDialog(
            onDismissRequest = { showDateDialog = false },
            confirmButton = {
                Button(onClick = {
                    showDateDialog = false
                    onDateSelected(datePickerState.selectedDateMillis)
                }) { Text("Confirmar") }
            },
            dismissButton = {
                OutlinedButton(onClick = { showDateDialog = false }) { Text("Cancelar") }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    if (showTimeDialog) {
        AlertDialog(
            onDismissRequest = { showTimeDialog = false },
            confirmButton = {
                Button(onClick = {
                    showTimeDialog = false
                    onTimeSelected(timePickerState.hour, timePickerState.minute)
                }) { Text("Confirmar") }
            },
            dismissButton = {
                OutlinedButton(onClick = {
                    showTimeDialog = false
                }) { Text("Cancelar") }
            },
            text = { TimePicker(state = timePickerState) }
        )
    }
}