package com.features.subject.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.NoteAdd
import androidx.compose.material.icons.filled.Notes
import androidx.compose.material.icons.filled.PostAdd
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.designsystem.components.Pill
import com.core.designsystem.components.fab.FabItem
import com.core.designsystem.components.fab.FabMenu
import com.core.designsystem.components.row.GridLazyRow
import com.core.designsystem.extensions.asLocalizedDate
import com.core.designsystem.extensions.toMinuteAndSecond
import com.example.model.Label
import com.example.model.Note
import com.example.model.Subject
import com.example.model.event.Exam

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectDetailScreen(
    onBackPressed: () -> Unit,
    detailState: SubjectDetailState,
    onNoteClicked: (Note) -> Unit,
    onDeleteButtonClicked: (Subject) -> Unit = {},
    onAddEventClicked: (String) -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(detailState.subjectCompound.subject.name) },
                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back button"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search button"
                        )
                    }

                    IconButton(onClick = { onDeleteButtonClicked(detailState.subjectCompound.subject) }) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Delete button"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FabMenu(
                items = listOf(
                    FabItem(Icons.Filled.Notes, "Nota"),
                    FabItem(Icons.Filled.NoteAdd, "Prova"),
                    FabItem(Icons.Filled.PostAdd, "Trabalho"),
                    FabItem(Icons.Filled.BookmarkAdd, "Customizado"),
                ),
                onFabClicked = {
                    when (it.icon) {
                        Icons.Filled.NoteAdd -> onAddEventClicked("prova")
                        Icons.Filled.PostAdd -> onAddEventClicked("trabalho")
                        else -> onAddEventClicked("custom")
                    }
                }
            )
        }
    ) { paddingValue ->
        Column(Modifier.padding(paddingValue)) {

            Text(detailState.subjectCompound.subject.teacher)
            Text(detailState.subjectCompound.subject.place)

            Text(text = "Grade horária")

            detailState.subjectCompound.timetables.entries.forEach {
                Text(text = it.key.asLocalizedDate())
                it.value.forEach { timetable ->
                    Text(timetable.startTime.toMinuteAndSecond())
                    Text(timetable.endTime.toMinuteAndSecond())
                    Divider()
                }
            }

            Text(modifier = Modifier.padding(horizontal = 8.dp), text = "Notas")

            GridLazyRow(list = detailState.notesWithLabelCompound) { note ->
                NoteItemCard(item = note.note, labels = note.labels, onNoteClicked = { onNoteClicked(note.note) })
            }

            Text("Proximos eventos")
            GridLazyRow(list = detailState.events) { event ->
                OutlinedCard {
                    Column(Modifier.padding(6.dp)) {
                        Text(event.event.name)
                        Pill(text = event.label.name, color = Color.Gray)
                        event.eventScore?.score?.let { Text("Vale $it pontos!") }
//                        Text(event.label.name)
//                        Text(text = event.event.eventLabels.first())
                    }
                }
            }

//            Text(modifier = Modifier.padding(horizontal = 8.dp), text = "Notificações")
//
//            GridLazyRow(list = notes) { note ->
//                NoteItemCard(item = note, onNoteClicked = { onNoteClicked(note) })
//            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteItemCard(item: Note, labels: List<Label>, onNoteClicked: (Note) -> Unit) {
    Card(onClick = { onNoteClicked(item) }) {
        Column(Modifier.padding(8.dp)) {
            Text(text = item.title, maxLines = 2)
            Text(text = item.body)

            Spacer(Modifier.height(4.dp))

            labels.forEach { Pill(it.name, Color.Blue.copy(0.4f)) }

//            Row {
//
//                Pill("Resumo", Color.Yellow.copy(0.4f))
////                Pill("Trabalho", Green.copy(0.4f))
//            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExamItemCard(item: Exam, onExamClicked: (Exam) -> Unit) {
    Card(onClick = { onExamClicked(item) }) {
        Column(Modifier.padding(8.dp)) {
            Text(text = item.name, maxLines = 2)

            Spacer(Modifier.height(4.dp))

            Pill("change thiss", Color.Blue.copy(0.4f))

//            Row {
//
//                Pill("Resumo", Color.Yellow.copy(0.4f))
////                Pill("Trabalho", Green.copy(0.4f))
//            }
        }
    }
}

@Preview
@Composable
fun SubjectDetailScreenPreview() {
    SubjectDetailScreen(
        onBackPressed = {},
        detailState = SubjectDetailState(),
        onNoteClicked = {},
    )
}