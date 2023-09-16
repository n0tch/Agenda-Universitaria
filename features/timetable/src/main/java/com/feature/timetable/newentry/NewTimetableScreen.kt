package com.feature.timetable.newentry

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedIconButton
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
import androidx.compose.ui.tooling.preview.Preview
import com.core.designsystem.components.chip.MultipleChipSelection
import com.core.designsystem.components.chip.SingleChipSelection
import com.core.designsystem.components.expandablecard.ExpandableCard
import com.core.designsystem.components.timepicker.AppTimePicker
import com.example.model.Subject
import com.example.model.Timetable
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.Locale
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTimetableScreen(
    subjects: List<Subject> = emptyList(),
    onSaveButtonClicked: (List<Timetable>, Int) -> Unit = {_,_->},
    onAddNewSubjectClicked: () -> Unit = {}
) {
    val selectedWeekDays: SnapshotStateList<DayOfWeek> = remember { mutableStateListOf() }
    val selectedTimetables: SnapshotStateMap<DayOfWeek, MutableList<Timetable>> = remember {
        mutableStateMapOf()
    }

    var subject by remember { mutableStateOf(-1) }

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
        Column {
            OutlinedCard(modifier = Modifier.fillMaxWidth()) {
                Text("Selecione a materia")
                SingleChipSelection(
                    items = subjects,
                    content = { Text(it.name) },
                    onSelection = { subject = it.id },
                    isLastItemSelected = true,
                    onLastItemClicked = { onAddNewSubjectClicked() }
                )
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
                                    AppTimePicker { start, end ->
                                        timetable.startTime =
                                            convertSelectedTime(start.hour, start.minute)
                                        timetable.endTime =
                                            convertSelectedTime(end.hour, end.minute)
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
        }


        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onSaveButtonClicked(selectedTimetables.values.flatten(), subject)
            },
            content = {
                Text("Save")
            }
        )
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
    NewTimetableScreen()
}