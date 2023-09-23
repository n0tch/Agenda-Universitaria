package com.features.subject.edit

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.core.designsystem.components.chip.MultipleChipSelection
import com.core.designsystem.components.chip.SingleChipSelection
import com.core.designsystem.components.expandablecard.ExpandableCard
import com.core.designsystem.components.timepicker.StartAndEndTimePicker
import com.example.model.Subject
import com.example.model.Timetable
import com.example.model.event.NotificationEarlier
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.Locale

@Composable
internal fun SubjectEditScreen(
    state: SubjectEditState,
    onAction: (SubjectEditAction.SaveSubject) -> Unit
) {

    var subjectName by remember { mutableStateOf("") }
    var placeName by remember { mutableStateOf("") }
    var teacherName by remember { mutableStateOf("") }

    val selectedWeekDays: SnapshotStateList<DayOfWeek> = remember { mutableStateListOf() }
    val selectedTimetables: SnapshotStateMap<DayOfWeek, MutableList<Timetable>> = remember {
        mutableStateMapOf()
    }

    var notification by remember { mutableStateOf(NotificationEarlier.DISABLED) }
    var notificationEnabled by remember { mutableStateOf(false) }

    Scaffold { paddingValue ->
        Column(Modifier.padding(paddingValue)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Add new discipline",
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                value = subjectName,
                singleLine = true,
                onValueChange = { subjectName = it },
                placeholder = { Text(text = "Nome da Disciplina") }
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                value = placeName,
                singleLine = true,
                onValueChange = { placeName = it },
                placeholder = { Text(text = "Sala") }
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                value = teacherName,
                singleLine = true,
                onValueChange = { teacherName = it },
                placeholder = { Text(text = "Professor") }
            )

            OutlinedCard(Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Notificação")
                    Switch(checked = notificationEnabled, onCheckedChange = { notificationEnabled = it })
                }
                AnimatedVisibility(visible = notificationEnabled) {
                    SingleChipSelection(
                        items = NotificationEarlier.values().toList(),
                        content = {
                            Text(it.name)
                        },
                        preSelected = { notification },
                        onSelection = { notification = it }
                    )
                }
            }

            OutlinedCard(modifier = Modifier.fillMaxWidth()) {
                Text("Selecione o(s) dia(s) da aula")

                MultipleChipSelection(
                    items = DayOfWeek.values().toList(),
                    content = {
                        Text(it.getDisplayName(TextStyle.FULL, Locale.getDefault()))
                    },
                    selectedItems = {
                        selectedWeekDays.clear()
                        selectedWeekDays.addAll(it)

                        it.forEach { dayOfWeek ->
                            if (!selectedTimetables.containsKey(dayOfWeek)) {
                                selectedTimetables[dayOfWeek] =
                                    mutableListOf(Timetable(weekDay = dayOfWeek.name))
                            }
                        }
                    }
                )
            }

            LazyColumn {
                items(selectedWeekDays) { dayOfWeek ->
                    ExpandableCard(
                        modifier = Modifier.fillMaxWidth(),
                        title = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
                    ) {

                        selectedTimetables[dayOfWeek]?.forEachIndexed { index, timetable ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row {
                                    StartAndEndTimePicker(dayOfWeek.value) { start, end ->
                                        timetable.startTime = start
                                        timetable.endTime = end
                                    }
                                }

                                Row {
                                    OutlinedIconButton(
                                        onClick = {
                                            selectedTimetables[dayOfWeek] =
                                                selectedTimetables[dayOfWeek]?.toMutableList()
                                                    ?.apply { removeAt(index) }
                                                    ?: mutableListOf()
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Remove,
                                            contentDescription = "remove item"
                                        )
                                    }
                                    OutlinedIconButton(
                                        onClick = {
                                            selectedTimetables[dayOfWeek] =
                                                selectedTimetables[dayOfWeek]?.toMutableList()
                                                    ?.apply { add(Timetable(weekDay = dayOfWeek.name)) }
                                                    ?: mutableListOf()
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Add,
                                            contentDescription = "add more"
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedButton(onClick = { /*onDismiss()*/ }) {
                    Text(text = "Cancelar")
                }
                Button(
                    onClick = {
                        onAction(
                            SubjectEditAction.SaveSubject(
                                subject = Subject(
                                    name = subjectName,
                                    place = placeName,
                                    teacher = teacherName,
                                ),
                                notificationEnabled = notificationEnabled,
                                timetable = selectedTimetables.values.flatten(),
                                notifyEarlier = notification
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