package com.features.note.edit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.core.designsystem.components.LoadingView
import com.core.designsystem.components.ToastComponent
import com.core.designsystem.dialogs.CommonSubjectAddDialog
import com.core.designsystem.dialogs.LabelAddDialog

@Composable
fun EditNoteComponent(
    onBackPressed: () -> Unit,
    noteId: Int,
    isNewNote: Boolean
) {
    val viewModel: EditNoteViewModel = hiltViewModel()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val labels by viewModel.labelState.collectAsStateWithLifecycle()
    val subjects by viewModel.subjects.collectAsStateWithLifecycle()
    var showLabelDialog by remember { mutableStateOf(false) }
    var showSubjectDialog by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit, block = {
        viewModel.fetchNote(noteId)
    })

    if (!uiState.isLoading) {
        NewNoteScreen(
            noteCompound = uiState.noteCompound,
            saved = uiState.noteSaved,
            noteLabels = labels,
            subjects = subjects,
            onSaveClicked = { note, mediaList, labelList ->
                if (isNewNote) {
                    viewModel.saveNote(note, mediaList, labelList)
                } else {
                    viewModel.updateNote(note, mediaList, labelList)
                }
            },
            onBackClicked = onBackPressed,
            addNewLabel = { showLabelDialog = true },
            addSubject = { showSubjectDialog = true }
        )
    } else {
        LoadingView()
    }

    if (showLabelDialog) {
        LabelAddDialog(
            onDismiss = { showLabelDialog = false },
            onSaveButtonClicked = {
                viewModel.saveNoteLabel(it)
                showLabelDialog = false
            }
        )
    }

    if (showSubjectDialog) {
        CommonSubjectAddDialog(
            onDismiss = { showSubjectDialog = false },
            onSaveButton = { subject ->
                viewModel.saveSubject(subject.name, subject.place, subject.teacher)
            }
        )
    }

    if (uiState.exception != null) {
        ToastComponent(message = uiState.exception!!.message.toString())
    }
}