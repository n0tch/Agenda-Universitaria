package com.home.list.adapters

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.core.designsystem.NoteItemCard
import com.core.designsystem.components.Pill
import com.example.model.Note
import com.example.model.NoteCompound

fun LazyGridScope.notesAdapter(
    notes: List<NoteCompound>,
    onNoteClicked: (Note) -> Unit = {},
    onSeeAll: () -> Unit = {},
) {
    item(span = { GridItemSpan(2) }) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = "Ultimas Notas",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                modifier = Modifier.padding(vertical = 8.dp).clickable { onSeeAll() },
                text = "Ver todos",
                fontSize = 14.sp,
                textAlign = TextAlign.End,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }

    items(notes, span = { GridItemSpan(1) }) {
        NoteItemCard(
            item = it,
            onNoteClicked = { onNoteClicked(it.note) }
        ) { item ->
            Text(
                text = item.note.title,
                maxLines = 2,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(text = item.note.body, color = MaterialTheme.colorScheme.onBackground)
            Text(
                text = "${item.uriPaths.size} medias",
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(Modifier.height(4.dp))

            Pill(item.subject.name, Color.Yellow.copy(0.4f))

            Row {
                item.labels.forEach {
                    Pill(it.name, Color.Blue.copy(0.4f))
                }
            }
        }
    }
}