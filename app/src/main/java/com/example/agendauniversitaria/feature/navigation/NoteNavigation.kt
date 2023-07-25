package com.example.agendauniversitaria.feature.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.agendauniversitaria.domain.model.Note
import com.example.agendauniversitaria.feature.notes.NewNoteComponent
import com.example.agendauniversitaria.feature.notes.NoteType

const val noteGraph = "note_graph"
private const val noteItemArgument = "note"

enum class NoteScreens(val route: String) {
    NOTE("new_note_route")
}

fun NavGraphBuilder.noteNavGraph(navController: NavController) {
    navigation(route = noteGraph, startDestination = NoteScreens.NOTE.route) {
        composable(route = NoteScreens.NOTE.route + "/{note}", arguments = listOf(navArgument(
            noteItemArgument
        ){
            type = NoteType()
        })) {
            val note = it.arguments?.getParcelable(noteItemArgument) ?: Note()
            NewNoteComponent(navController = navController, note)
        }

        composable(route = NoteScreens.NOTE.route){
            NewNoteComponent(navController = navController, Note())
        }
    }
}