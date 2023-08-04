package com.feature.navigation.note

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.features.note.list.NoteListComponent
import com.features.note.newnote.NoteComponent

const val noteGraphRoute = "note_graph"

fun NavGraphBuilder.noteNavGraph(navController: NavController) {
    navigation(route = noteGraphRoute, startDestination = NoteScreens.NOTE_LIST.route) {
        composable(route = NoteScreens.NOTE.route + "/{id}") { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("id")
            NoteComponent(navController = navController, noteId = noteId)
        }

        composable(route = NoteScreens.NOTE_LIST.route) {
            NoteListComponent(onNavigateToNote = { noteId ->
                navController.navigate(NoteScreens.NOTE.route + "/$noteId")
            })
        }
    }
}

fun NavController.navigateToNotes() {
    navigate(noteGraphRoute)
}
