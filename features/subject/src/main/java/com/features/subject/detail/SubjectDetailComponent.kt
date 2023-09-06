package com.features.subject.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SubjectDetailComponent(
    onBackPressed: () -> Unit,
    subjectName: String,
    subjectId: Int,
    onNavigateToNote: (Int) -> Unit
) {

    val viewModel: SubjectDetailViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.fetchSubjectCompound(subjectId)
    })

    SubjectDetailScreen(
        onBackPressed = { onBackPressed() },
        subjectName = subjectName,
        notes = uiState.subjectCompound.notes,
        exams = uiState.subjectCompound.exams,
        onNoteClicked = { onNavigateToNote(it.id) },
        onDeleteButtonClicked = {
            viewModel.deleteSubject(subjectId)
        }
    )
}
