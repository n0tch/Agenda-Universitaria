package com.features.subject.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.designsystem.components.Pill
import com.core.designsystem.components.row.GridLazyRow
import com.example.model.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectDetailScreen(
    onBackPressed: () -> Unit,
    subjectName: String = "Test 1",
    notes: List<Note>,
    onNoteClicked: (Note) -> Unit,
    onDeleteButtonClicked: () -> Unit = {}
) {

    val scroll = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(subjectName) },
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

                    IconButton(onClick = { onDeleteButtonClicked() }) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Delete button"
                        )
                    }
                }
            )
        }
    ) { paddingValue ->
        Column(Modifier.padding(paddingValue)) {

            Text(modifier = Modifier.padding(horizontal = 8.dp), text = "Notas")

            GridLazyRow(list = notes) { note ->
                NoteItemCard(item = note, onNoteClicked = { onNoteClicked(note) })
            }

            Text(modifier = Modifier.padding(horizontal = 8.dp), text = "Provas")

            GridLazyRow(list = notes) { note ->
                NoteItemCard(item = note, onNoteClicked = { onNoteClicked(note) })
            }

            Text(modifier = Modifier.padding(horizontal = 8.dp), text = "Notificações")

            GridLazyRow(list = notes) { note ->
//                NoteItemCard(item = note, onNoteClicked = { onNoteClicked(note) })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteItemCard(item: Note, onNoteClicked: (Note) -> Unit) {
    Card(onClick = { onNoteClicked(item) }) {
        Column(Modifier.padding(8.dp)) {
            Text(text = item.title, maxLines = 2)
            Text(text = item.body)

            Spacer(Modifier.height(4.dp))

            Pill(item.label, Color.Blue.copy(0.4f))

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
        "",
        listOf(Note(title = "nota 1"), Note(title = "nota 2")),
        onNoteClicked = {},
    )
}