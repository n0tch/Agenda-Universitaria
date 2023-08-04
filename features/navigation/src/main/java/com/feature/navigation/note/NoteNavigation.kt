package com.feature.navigation.note

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.features.note.home.NoteComponent
import com.features.note.newnote.NewNoteComponent

const val noteGraphRoute = "note_graph"
const val NOTE_ITEM_ARGUMENT = "note"

fun NavGraphBuilder.noteNavGraph(navController: NavController) {
    navigation(route = noteGraphRoute, startDestination = NoteScreens.NOTE_LIST.route) {
        composable(route = NoteScreens.NOTE.route + "/{id}") { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("id") ?: ""
            NewNoteComponent(navController = navController, noteId)
        }

        composable(route = NoteScreens.NOTE_LIST.route) {
            NoteComponent(onNavigateToNote = {
                navController.navigate(NoteScreens.NOTE.route)
            })
        }
    }
}

fun NavController.navigateToNotes() {
    navigate(noteGraphRoute)
}
