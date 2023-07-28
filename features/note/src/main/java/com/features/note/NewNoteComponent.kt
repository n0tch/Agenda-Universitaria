package com.features.note

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
    var noteLabelList: List<String> = remember { mutableStateListOf() }

    when (uiState) {
        is NoteState.NoteException -> {}
        NoteState.NoteIdle -> {}
        NoteState.NoteLoading -> { save = false }
        is NoteState.NoteSaved -> {
            save = true
        }

        is NoteState.NoteLabels -> {
            noteLabelList = (uiState as NoteState.NoteLabels).labels
        }
    }

    NewNoteScreen(
        note = note,
        saved = save,
        noteLabels = noteLabelList,
        onSaveClicked = { title, desc, label ->
            viewModel.saveNote(title, desc, label)
        },
        onBackClicked = {
            navController.popBackStack()
        }
    )
}