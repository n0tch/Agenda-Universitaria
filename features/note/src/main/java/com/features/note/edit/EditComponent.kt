package com.features.note.edit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.core.designsystem.components.LoadingView
import com.core.designsystem.components.ToastComponent

@Composable
fun NoteComponent(
    onBackPressed: () -> Unit,
    noteId: Int,
    isNewNote: Boolean
) {
    val viewModel: EditNoteViewModel = hiltViewModel()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val labelState by viewModel.labelState.collectAsStateWithLifecycle()
    val subjectState by viewModel.subjectsState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.fetchNote(noteId)
    })

    if (!uiState.isLoading) {
        NewNoteScreen(
            noteCompound = uiState.noteCompound,
            saved = uiState.noteSaved,
            medias = uiState.noteCompound.uriPaths,
            noteLabels = labelState.labels,
            subjects = subjectState.subjects,
            onSaveClicked = { note, mediaList, labels ->
                if (isNewNote) {
                    viewModel.saveNote(note, mediaList, labels)
                } else {
                    viewModel.updateNote(note, mediaList, labels)
                }
            },
            onBackClicked = onBackPressed,
            onSaveLabel = viewModel::saveNoteLabel
        )
    } else {
        LoadingView()
    }

    if(uiState.exception != null){
        ToastComponent(message = uiState.exception!!.message.toString())
    }
}