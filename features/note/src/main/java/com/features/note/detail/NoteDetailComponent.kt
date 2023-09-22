package com.features.note.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun NoteDetailComponent(
    noteId: Int,
    onBackPressed: ()-> Unit,
    onEditNotePressed: (Int) -> Unit
) {

    val viewModel = hiltViewModel<NoteDetailViewModel>()
    val state by viewModel.collectAsState()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.fetchNoteDetail(noteId)
    })

    NoteDetailScreen(
        state = state,
        onBackPressed = onBackPressed,
        onEditNotePressed = onEditNotePressed
    )
}
