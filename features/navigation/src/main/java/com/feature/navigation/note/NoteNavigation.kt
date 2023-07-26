package com.feature.navigation.note

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.features.note.NewNoteComponent
import com.features.note.NoteType

const val noteGraphRoute = "note_graph"
private const val noteItemArgument = "note"

fun NavGraphBuilder.noteNavGraph(navController: NavController) {
    navigation(route = noteGraphRoute, startDestination = NoteScreens.NOTE.route) {
        composable(route = NoteScreens.NOTE.route + "/{note}", arguments = listOf(navArgument(
            noteItemArgument
        ){
            type = NoteType()
        })
        ) {
//            val note = it.arguments?.getParcelable(noteItemArgument)
            NewNoteComponent(navController = navController)
        }

        composable(route = NoteScreens.NOTE.route){
            NewNoteComponent(navController = navController)
        }
    }
}