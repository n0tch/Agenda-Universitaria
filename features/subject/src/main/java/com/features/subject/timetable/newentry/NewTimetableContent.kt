package com.features.subject.timetable.newentry

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.model.Subject

@Composable
fun NewTimeTableContent() {
    val viewModel: NewTimetableViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var subjects: List<Subject> = remember { mutableStateListOf() }

    when (uiState) {
        NewTimetableState.Idle -> {}
        is NewTimetableState.Subjects -> {
            subjects = (uiState as NewTimetableState.Subjects).subjects
        }
    }

    NewTimetableScreen(
        subjects = subjects,
        onSaveButtonClicked = {
            viewModel.saveTimetable(it)
        }
    )
}

@Preview
@Composable
fun NewTimeTableContentPreview() {
    NewTimeTableContent()
}