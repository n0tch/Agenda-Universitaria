package com.features.event.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.model.event.EventCompound

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ListEventScreen(
    events: List<EventCompound>,
    onBackPressed: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de eventos") },
                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
    ) { paddingValues ->
        Column(Modifier.padding(paddingValues)){
            LazyColumn{
                items(events){ eventCompound ->
                    OutlinedCard {
                        Column(Modifier.padding(4.dp)) {
                            Text(text = eventCompound.event.name)
                            Text(text = eventCompound.label.name)
                        }
                    }
                }
            }
        }
    }
}