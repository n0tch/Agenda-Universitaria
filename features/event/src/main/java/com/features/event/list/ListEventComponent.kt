package com.features.event.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun ListEventComponent(
    onBackPressed: () -> Unit,
) {

    val viewModel: ListEventViewModel = hiltViewModel()
    val state by viewModel.collectAsState()

    LaunchedEffect(Unit){
        viewModel.fetchEvents()
    }

    ListEventScreen(
        events = state.events,
        onBackPressed = { onBackPressed() }
    )
}