package com.features.subject.detail

import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.model.Note

@Composable
fun SubjectDetailComponent(
    onBackPressed: () -> Unit,
    subjectName: String,
    onNavigateToNote: (Note) -> Unit
) {

    val viewModel: SubjectDetailViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var noteList: List<Note> = remember { mutableStateListOf() }

    viewModel.fetchNotesBySubject(subjectName)

    when(uiState){
        is SubjectDetailState.Error -> {}
        SubjectDetailState.Idle -> {}
        SubjectDetailState.Loading -> {}
        is SubjectDetailState.NoteList -> {
            noteList = (uiState as SubjectDetailState.NoteList).items
        }
    }

    SubjectDetailScreen(
        onBackPressed = { onBackPressed() },
        subjectName = subjectName,
        notes = noteList,
        onNoteClicked = { onNavigateToNote(it) }
    )
}
