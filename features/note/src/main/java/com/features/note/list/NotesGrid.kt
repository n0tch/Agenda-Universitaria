package com.features.note.list

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.designsystem.NoteItemCard
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
            onNoteClicked = { item -> onNoteClicked(item.note) },
            content = { item ->
                Text(text = item.note.title, maxLines = 2)
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
        )
    }
}

@Preview
@Composable
fun NotesGripPreview() {
    NotesGrid(listOf()) {}
}