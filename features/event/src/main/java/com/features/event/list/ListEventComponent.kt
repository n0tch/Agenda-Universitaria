package com.features.event.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ListEventComponent() {

    val viewModel: ListEventViewModel = hiltViewModel()
    val events by viewModel.events.collectAsStateWithLifecycle()

    ListEventScreen(events)
}