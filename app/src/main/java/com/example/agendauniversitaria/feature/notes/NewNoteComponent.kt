package com.example.agendauniversitaria.feature.notes

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.agendauniversitaria.domain.model.Note

const val NOTE_DETAIL_PARCELABLE = "NOTE_DETAIL_PARCELABLE"

@Composable
fun NewNoteComponent(navController: NavController, note: Note) {

    val viewModel: NoteViewModel = hiltViewModel()

    NewNoteScreen(
        note = note,
        onBackClicked = {
        navController.popBackStack()
    })
}