package com.features.note

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.model.Note

@Composable
fun NewNoteComponent(navController: NavController, note: Note = Note()) {

    val viewModel: NoteViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var save by remember { mutableStateOf(false) }

    when (uiState) {
        is NoteState.NoteException -> {}
        NoteState.NoteIdle -> {}
        NoteState.NoteLoading -> { save = false }
        is NoteState.NoteSaved -> {
            save = true
        }
    }

    NewNoteScreen(
        note = note,
        saved = save,
        onSaveClicked = { title, desc ->
            viewModel.saveNote(title, desc)
        },
        onBackClicked = {
            navController.popBackStack()
        }
    )
}