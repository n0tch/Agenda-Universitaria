package com.features.event.edit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.core.designsystem.components.LoadingView
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun EditEventComponent(
    subject: Int = -1,
    onBackPressed: () -> Unit = {},
) {

    val viewModel: EditEventViewModel = hiltViewModel()
    val state by viewModel.collectAsState()

    if(state.isLoading){
        LoadingView()
    } else {
        EditEventScreen(
            subjectSelected = subject,
            state = state,
            onAction = { viewModel.setAction(it) }
        )
    }

    viewModel.collectSideEffect{
        when(it){
            EditEventSideEffect.OnBack -> onBackPressed()
        }
    }
}
