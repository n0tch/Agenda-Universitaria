package com.features.note.newnote

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController

@Composable
fun NoteComponent(
    navController: NavController,
    noteId: Int?
) {
    val viewModel: NewNoteViewModel = hiltViewModel()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val labelState by viewModel.labelState.collectAsStateWithLifecycle()
    val subjectState by viewModel.subjectsState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.fetchNote(noteId)
    })

    NewNoteScreen(
        note = uiState.noteCompound.note,
        saved = uiState.noteSaved,
        medias = uiState.noteCompound.uriPaths,
        noteLabels = labelState.labels,
        subjects = subjectState.subjects,
        onSaveClicked = { note, uriPaths, labels ->
            viewModel.saveNote(note, uriPaths, labels)
        },
        onBackClicked = {
            navController.popBackStack()
        },
        onSaveLabel = {
            viewModel.saveNoteLabel(it)
        }
    )
}