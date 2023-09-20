package com.features.event.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.model.event.EventCompound

@Composable
internal fun ListEventScreen(
    events: List<EventCompound>
) {
    LazyColumn{
        items(events){ eventCompound ->
            OutlinedCard {
                Column(Modifier.padding(4.dp)) {
                    Text(text = eventCompound.event.name)
                }
            }
        }
    }
}