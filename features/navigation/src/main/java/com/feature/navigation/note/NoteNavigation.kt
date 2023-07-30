package com.feature.navigation.note

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.model.Note
import com.features.note.NewNoteComponent
import com.features.note.NoteType

const val noteGraphRoute = "note_graph"
const val NOTE_ITEM_ARGUMENT = "note"

fun NavGraphBuilder.noteNavGraph(navController: NavController) {
    navigation(route = noteGraphRoute, startDestination = NoteScreens.NOTE.route) {
        composable(route = NoteScreens.NOTE.route) {
            val note = navController
                .previousBackStackEntry
                ?.savedStateHandle
                ?.get<Note>(NOTE_ITEM_ARGUMENT)

            NewNoteComponent(navController = navController, note = note ?: Note())
        }

//        composable(route = NoteScreens.NOTE.route) {
//            NewNoteComponent(navController = navController)
//        }
    }
}

fun NavController.navigateToNotes() {
    navigate(NoteScreens.NOTE.route)
}

fun NavController.navigateToNotesWithItem(note: Note){
    currentBackStackEntry?.savedStateHandle?.set(
        key = NOTE_ITEM_ARGUMENT,
        value = note
    )
    navigate(NoteScreens.NOTE.route)
}