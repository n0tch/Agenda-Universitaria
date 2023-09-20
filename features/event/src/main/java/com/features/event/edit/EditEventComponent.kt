package com.features.event.edit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun EditEventComponent(
    subject: Int = -1,
    onNavigation: (EditEventNavigation) -> Unit
) {

    val viewModel: EditEventViewModel = hiltViewModel()

    val actionState by viewModel.actionState.observeAsState()
    val subjects by viewModel.subjects.collectAsStateWithLifecycle()
    val labels by viewModel.labels.collectAsStateWithLifecycle()

    LaunchedEffect(actionState) {
        when (val action = actionState) {
            is EditEventAction.SaveEvent -> {
                viewModel.saveEvent(action.eventCompound, action.subjectId)
            }

            EditEventAction.OnBack -> { onNavigation(EditEventNavigation.OnBack) }
            null -> {}
        }
    }

    EditEventScreen(
        subjectSelected = subjects.firstOrNull { it.id == subject },
        subjects = subjects,
        labels = labels,
        onAction = { viewModel.setAction(it) }
    )
}
