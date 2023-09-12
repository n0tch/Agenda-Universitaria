package com.features.note.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.designsystem.components.Pill
import com.core.designsystem.components.row.GridLazyRow
import com.example.model.Note
import com.example.model.NoteCompound

@Composable
fun NotesGrid(
    notes: List<NoteCompound> = emptyList(),
    onNoteClicked: (Note) -> Unit = {},
    onNoteSelected: (Note) -> Unit = {},
    onDeleteClicked: (Note) -> Unit = {}
) {
    GridLazyRow(list = notes) {
        NoteItemCard(
            item = it,
            onNoteClicked = onNoteClicked,
            onNoteSelected = onNoteSelected,
            onDeleteClicked = onDeleteClicked
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteItemCard(
    item: NoteCompound,
    onNoteClicked: (Note) -> Unit,
    onNoteSelected: (Note) -> Unit,
    onDeleteClicked: (Note) -> Unit
) {
    var enableSelection by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier.combinedClickable(
            onClick = { onNoteClicked(item.note) },
            onLongClick = {
                onNoteSelected(item.note)
                enableSelection = enableSelection.not()
            }
        )
    ) {
        Column(
            Modifier
                .padding(8.dp)
                .fillMaxSize()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = item.note.title, maxLines = 2)
                if(enableSelection){
                    Checkbox(checked = true, onCheckedChange = {})
                }
                IconButton(onClick = { onDeleteClicked(item.note) }){
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = "Add Label")
                }
            }
            Text(text = item.note.body)
            Text(text = "${item.uriPaths.size} medias")

            Spacer(Modifier.height(4.dp))

            Pill(item.subject.name, Yellow.copy(0.4f))

            Row {
                item.labels.forEach {
                    Pill(it.name, Blue.copy(0.4f))
                }
            }
        }
    }
}

@Preview
@Composable
fun NotesGripPreview() {
    NotesGrid(listOf()) {}
}