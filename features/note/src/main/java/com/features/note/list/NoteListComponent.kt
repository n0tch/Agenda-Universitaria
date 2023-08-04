package com.features.note.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.core.designsystem.components.row.PillLazyRow
import com.example.model.Note

@Composable
fun NoteListComponent(
    onNavigateToNote: (id: String?) -> Unit,
) {

    val viewModel: NoteListViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var notes: List<Note> = remember { mutableStateListOf() }

    when(uiState){
        is NoteListState.Error -> {}
        NoteListState.Idle -> {}
        NoteListState.Loading -> {}
        is NoteListState.NoteList -> {
            notes = (uiState as NoteListState.NoteList).notes
        }
    }

    Scaffold(
        modifier = Modifier,
        floatingActionButton = {
            FloatingActionButton(onClick = { onNavigateToNote(null) }) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add fab")
            }
        }
    ) {
        Column(Modifier.padding(it)) {
            HomeNotesHeaderComponent(onNoteClicked = { note -> onNavigateToNote(note.id) })
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
            NotesGrid(
                notes = notes,
                onNoteClicked = { note -> onNavigateToNote(note.id) }
            )
        }
    }
}

@Preview
@Composable
fun HomeComponentPreview() {
    NoteListComponent(onNavigateToNote = {})
}