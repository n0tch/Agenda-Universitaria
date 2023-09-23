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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.core.designsystem.components.chip.SingleChipSelection
import com.core.designsystem.components.colorpicker.ColorPicker
import com.core.designsystem.components.timepicker.DateTimePicker
import com.example.model.event.NotificationPeriod

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun EditEventScreen(
    subjectSelected: Int,
    state: EditEventState,
    onAction: (EditEventAction) -> Unit = {}
) {
    val event = remember { NewEventDataState() }

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
                    value = event.name.value,
                    onValueChange = { event.name.value = it })

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedCard(modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Selectione a materia")
                    SingleChipSelection(
                        items = state.subjects,
                        content = {
                            Text(it.name)
                        },
                        preSelected = { state.subjects.firstOrNull { it.id == subjectSelected } },
                        onSelection = { event.subjectId.value = it.id }
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
                        onSelection = { event.label.value = it }
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Habilitar notificacao?")
                    Switch(
                        checked = event.hasNotification.value,
                        onCheckedChange = { event.hasNotification.value = it })
                }

                AnimatedVisibility(visible = event.hasNotification.value) {
                    Column {
                        DateTimePicker(
                            onDateSelected = { date -> date?.let { event.date.value = it } },
                            onTimeSelected = { hour, minute ->
                                event.hour.value = hour
                                event.minute.value = minute
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
                                    event.notificationPeriod.value = it.name
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
                        checked = event.hasScore.value,
                        onCheckedChange = { event.hasScore.value = it })
                }

                AnimatedVisibility(visible = event.hasScore.value) {
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = event.score.value,
                        onValueChange = { event.score.value = it },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                    )
                }

                ColorPicker(title = "Selecione a cor de identificação", onColorSelected = { event.color.value = it })

                Button(
                    onClick = {
                        onAction(
                            EditEventAction.SaveEvent(event.toEventData())
                        )
                    }
                ) {
                    Text(text = "Salvar")
                }
            }
        }
    }
}