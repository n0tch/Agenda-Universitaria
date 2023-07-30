package com.home.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.designsystem.components.Pill
import com.core.designsystem.components.row.GridLazyRow
import com.example.model.Note

@Composable
fun NotesGrid(onNoteClicked: (Note) -> Unit) {
    GridLazyRow(list = (1..20).toList()) {
        NoteItemCard(Note(title = "Titule $it", body = "hsauis u iuhsa uihs aiuhisuahauuu auahsuahlksa asas"), onNoteClicked)
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

            Row {
                Pill("Prova", Blue.copy(0.4f))
                Pill("Resumo", Yellow.copy(0.4f))
//                Pill("Trabalho", Green.copy(0.4f))
            }
        }
    }
}

@Preview
@Composable
fun NotesGripPreview() {
    NotesGrid({ })
}