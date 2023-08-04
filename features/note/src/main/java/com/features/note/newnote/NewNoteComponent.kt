package com.features.note.newnote

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
import com.example.model.Subject

@Composable
fun NewNoteComponent(
    navController: NavController,
    noteId: String
) {

    val viewModel: NewNoteViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var save by remember { mutableStateOf(false) }
    val noteLabelList: List<DefaultNoteEnum> = DefaultNoteEnum.values().toList()
    var subjectList: List<Subject> = remember { mutableStateListOf() }

    when (uiState) {
        is NoteState.NoteException -> {}
        NoteState.NoteIdle -> {}
        NoteState.NoteLoading -> {
            save = false
        }

        is NoteState.NoteSaved -> {
            save = true
        }

        is NoteState.NoteLabels -> {
//            noteLabelList = (uiState as NoteState.NoteLabels).labels
        }

        is NoteState.SubjectList -> {
            subjectList = (uiState as NoteState.SubjectList).items
        }
    }

    //TODO: fetch note by id
    NewNoteScreen(
        note = Note(),
        saved = save,
        noteLabels = noteLabelList,
        subjects = subjectList,
        onSaveClicked = { title, desc, label, subject ->
            viewModel.saveNote(title, desc, label, subject)
        },
        onBackClicked = {
            navController.popBackStack()
        }
    )
}