package com.features.event.edit

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.core.designsystem.components.chip.SingleChipSelection
import com.core.designsystem.components.timepicker.DateTimePicker
import com.example.model.event.EventSaveRequest
import com.example.model.event.NotificationPeriod

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun EditEventScreen(
    subjectSelected: Int,
    state: EditEventState,
    onAction: (EditEventAction) -> Unit = {}
) {

    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var notificationEnabled by remember { mutableStateOf(false) }
    var notificationDate: Long? by remember { mutableStateOf(null) }
    var notificationHour by remember { mutableStateOf(0) }
    var notificationMinute by remember { mutableStateOf(0) }
    var scoreEnabled by remember { mutableStateOf(false) }
    var scoreValue: Float? by remember { mutableStateOf(null) }
    var subject by remember { mutableStateOf(0) }
    var label by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Cadastrar novo evento") },
                navigationIcon = {
                    IconButton(onClick = { onAction(EditEventAction.OnBack) }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
            Column(
                Modifier
                    .padding(padding)
                    .verticalScroll(rememberScrollState()),
            ) {
                Text(text = "Obs: um evento pode ser uma prova, trabalho, seminario etc.")

                Spacer(modifier = Modifier.height(24.dp))

                Text(text = "Informe o nome")
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = name,
                    onValueChange = { name = it })

                Text(text = "Informe a descriçao")
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = description,
                    onValueChange = { description = it })

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedCard(modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Selectione a materia")
                    SingleChipSelection(
                        items = state.subjects,
                        content = {
                            Text(it.name)
                        },
                        preSelected = { state.subjects.firstOrNull { it.id == subjectSelected } },
                        onSelection = { subject = it.id }
                    )
                }

                OutlinedCard(modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Selectione a categoria")
                    SingleChipSelection(
                        items = state.labels,
                        content = {
                            Text(it.name)
                        },
                        isLastItemSelected = true,
                        onLastItemClicked = {

                        },
                        onSelection = { label = it.id }
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Habilitar notificacao?")
                    Switch(
                        checked = notificationEnabled,
                        onCheckedChange = { notificationEnabled = it })
                }

                AnimatedVisibility(visible = notificationEnabled) {
                    Column {
                        DateTimePicker(
                            onDateSelected = { date -> date?.let { notificationDate = it } },
                            onTimeSelected = { hour, minute ->
                                notificationHour = hour
                                notificationMinute = minute
                            }
                        )
                        OutlinedCard(modifier = Modifier.fillMaxWidth()) {
                            Text("Selecione a periodicidade")
                            SingleChipSelection(
                                items = NotificationPeriod.values().toList(),
                                content = {
                                    Text(it.name)
                                },
                                onSelection = {

                                }
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Vale ponto?")
                    Switch(
                        checked = scoreEnabled,
                        onCheckedChange = { scoreEnabled = it })
                }

                AnimatedVisibility(visible = scoreEnabled) {
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = "$scoreValue",
                        onValueChange = { scoreValue = it.toFloat() },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                    )
                }

                Button(
                    onClick = {
                        onAction(
                            EditEventAction.SaveEvent(
                                EventSaveRequest(
                                    event = name,
                                    subjectId = subject,
                                    labelId = label,
                                    score = scoreValue,
                                    date = notificationDate,
                                    hour = notificationHour,
                                    minute = notificationMinute
                                )
                            )
                        )
                    }
                ) {
                    Text(text = "Salvar")
                }
            }
        }
    }
}