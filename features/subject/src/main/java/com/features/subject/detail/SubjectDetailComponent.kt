package com.features.subject.detail

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun SubjectDetailComponent(
    onBackPressed: () -> Unit,
    subjectName: String,
    subjectId: Int,
    onNavigateToNote: (Int) -> Unit,
    navigateToAddEvent: (Int) -> Unit
) {

    val context = LocalContext.current
    val viewModel: SubjectDetailViewModel = hiltViewModel()
    val state by viewModel.collectAsState()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.fetchSubject(subjectId)
        viewModel.fetchEvents(subjectId)
        viewModel.fetchTimetables(subjectId)
    })

    SubjectDetailScreen(
        onBackPressed = { onBackPressed() },
        detailState = state,
        onNoteClicked = { onNavigateToNote(it.id) },
        onDeleteButtonClicked = {
            viewModel.deleteSubject(it)
        },
        onAddEventClicked = { navigateToAddEvent(subjectId) }
    )

    viewModel.collectSideEffect{
        when(it){
            is SubjectDetailSideEffect.Toast -> Toast.makeText(
                context,
                it.message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
