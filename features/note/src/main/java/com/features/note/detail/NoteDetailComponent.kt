package com.features.note.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun NoteDetailComponent(
    noteId: Int,
    onBackPressed: ()-> Unit,
    onEditNotePressed: (Int) -> Unit
) {

    val viewModel = hiltViewModel<NoteDetailViewModel>()
    val detailState by viewModel.noteDetailState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.fetchNoteDetail(noteId)
    })

    NoteDetailScreen(
        noteCompound = detailState.noteCompound,
        onBackPressed = onBackPressed,
        onEditNotePressed = onEditNotePressed
    )
}
