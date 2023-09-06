package com.features.note.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.core.designsystem.components.row.PillItem
import com.core.designsystem.components.row.PillLazyRow
import com.example.model.Note

@Composable
fun NoteListComponent(
    onNavigateToNote: (id: Int?) -> Unit,
    shouldReturnSelectedNotes: Boolean = false,
    onNotesSelected: (List<String>) -> Unit = {}
) {

    val viewModel: NoteListViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val labelState by viewModel.labelState.collectAsStateWithLifecycle()
    val selectedNotes: MutableList<Note> = mutableListOf()

    Scaffold(
        modifier = Modifier,
        floatingActionButton = {
            FloatingActionButton(onClick = { onNavigateToNote(null) }) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add fab")
            }
        }
    ) {
        Column(Modifier.padding(it)) {
            HomeNotesHeaderComponent(
                onNoteClicked = { note ->
                    onNavigateToNote(note.id)
                },
                onSearch = { search ->
                    viewModel.searchNote(search)
                }
            )
            PillLazyRow(
                pillList = labelState.labels,
                content = {label ->
                    PillItem(label.name, Color.LightGray)
                },
                onClick = { label ->
                    viewModel.searchNotesByLabel(label)
                }
            )
            NotesGrid(
                notes = uiState.notes,
                onNoteClicked = { note -> onNavigateToNote(note.id) },
                onNoteSelected = { note -> selectedNotes.add(note) },
                onDeleteClicked = { note -> viewModel.deleteNote(note) }
            )

            if (shouldReturnSelectedNotes) {
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onNotesSelected(selectedNotes.map { note -> note.title }) }
                ) {
                    Text("Selecionar notas")
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeComponentPreview() {
    NoteListComponent(onNavigateToNote = {})
}