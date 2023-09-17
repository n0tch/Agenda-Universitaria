package com.core.designsystem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T> NoteItemCard(
    item: T,
    onNoteClicked: (T) -> Unit,
    content: @Composable (T) -> Unit
) {
    OutlinedCard(
        modifier = Modifier
            .clickable { onNoteClicked(item) }
            .padding(horizontal = 2.dp, vertical = 2.dp)
    ) {
        Column(modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()) {
            content(item)
        }
    }
}