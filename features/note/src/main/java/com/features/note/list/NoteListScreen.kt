package com.features.note.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.core.designsystem.components.row.PillItem
import com.core.designsystem.components.row.PillLazyRow
import com.example.model.Label
import com.example.model.Note

@Composable
fun NoteListScreen(
    state: NoteListState,
    onSearchNote: (String) -> Unit = {},
    onBackPressed: () -> Unit = {},
    onNavigateToNewNote: () -> Unit = {},
    onDeleteNote: (Note) -> Unit = {},
    onNavigateToNote: (Int) -> Unit = {},
    onSearchByLabel: (Label) -> Unit = {},
    onFilterClicked: () -> Unit = {}
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            HomeNotesHeaderComponent(
                onSearch = { onSearchNote(it) },
                onFilter = { onFilterClicked() },
                onBackPressed = onBackPressed
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToNewNote ) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add fab")
            }
        }
    ) {
        Column(
            Modifier
                .padding(it)
                .padding(top = 8.dp)) {
            PillLazyRow(
                pillList = state.labels,
                content = { label ->
                    PillItem(label.name, Color.LightGray)
                },
                onClick = { label ->
                    onSearchByLabel(label)
                }
            )
            NotesGrid(
                notes = state.notes,
                onNoteClicked = { note -> onNavigateToNote(note.id) },
                onDeleteClicked = { note -> onDeleteNote(note) }
            )
        }
    }
}