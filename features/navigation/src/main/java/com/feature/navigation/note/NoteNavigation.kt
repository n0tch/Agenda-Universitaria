package com.feature.navigation.note

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import com.features.note.detail.NoteDetailComponent
import com.features.note.edit.EditNoteComponent
import com.features.note.list.NoteListComponent

const val noteGraphRoute = "note_graph"

enum class NoteRoutes(val route: String){
    DETAIL(NoteScreens.NOTE_DETAIL.route + "/{id}")
}

fun NavGraphBuilder.noteNavGraph(navController: NavController) {
    navigation(route = noteGraphRoute, startDestination = NoteScreens.NOTE_LIST.route) {
        composable(route = NoteScreens.NOTE_EDITION.route + "/{id}") {
            val noteId = it.arguments?.getString("id")?.toIntOrNull() ?: -1
            EditNoteComponent(
                onBackPressed = { navController.popBackStack() },
                noteId = noteId,
                isNewNote = noteId == -1
            )
        }

        composable(route = NoteScreens.NOTE_LIST.route) {
            NoteListComponent(
                onNavigateToNote = { noteId ->
                    navController.navigate(NoteScreens.NOTE_DETAIL.route + "/$noteId")
                },
                onNavigateToNewNote = {
                    navController.navigate(NoteScreens.NOTE_EDITION.route + "/-1")
                },
                onBackPressed = navController::popBackStack
            )
        }

        composable(route = NoteScreens.NOTE_LIST_WITH_RESULT.route) {
            NoteListComponent(
                onNavigateToNote = {},
            )
        }

        composable(
            route = NoteRoutes.DETAIL.route,
            deepLinks = listOf(
                navDeepLink { uriPattern = NoteRoutes.DETAIL.route }
            )
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 0
            NoteDetailComponent(
                noteId = noteId,
                onBackPressed = {
                    navController.popBackStack()
                },
                onEditNotePressed = { id ->
                    navController.navigateToNoteEdition(id)
                }
            )
        }
    }
}

fun NavController.navigateToNotes() {
    navigate(noteGraphRoute){
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavController.navigateToNoteDetail(noteId: Int){
    navigate(NoteScreens.NOTE_DETAIL.route + "/$noteId")
}

fun NavController.navigateToNoteEdition(noteId: Int){
    navigate(NoteScreens.NOTE_EDITION.route + "/$noteId")
}