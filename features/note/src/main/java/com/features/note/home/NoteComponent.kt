package com.features.note.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.core.designsystem.components.row.PillLazyRow
import com.example.model.Note
import com.features.note.NotesGrid

@Composable
fun NoteComponent(
    onNavigateToNote: (Note) -> Unit,
) {
    Scaffold(
        modifier = Modifier,
        floatingActionButton = {
            FloatingActionButton(onClick = {  }) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add fab")
            }
        }
    ) {
        Column(Modifier.padding(it)) {
            HomeNotesHeaderComponent(onNoteClicked = { note -> onNavigateToNote(note) })
            PillLazyRow(
                pillList = listOf(
                    "Prova",
                    "Trabalho",
                    "Resumo",
                    "Prova",
                    "Trabalho",
                    "Resumo",
                    "Prova",
                    "Trabalho",
                    "Resumo",
                    "Prova",
                    "Trabalho",
                    "Resumo",
                )
            )
            NotesGrid(onNoteClicked = { note -> onNavigateToNote(note) })
        }
    }
}

@Preview
@Composable
fun HomeComponentPreview() {
    NoteComponent(onNavigateToNote = {})
}