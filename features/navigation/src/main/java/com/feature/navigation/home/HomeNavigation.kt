package com.feature.navigation.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.feature.navigation.HomeMainScreens
import com.feature.navigation.exam.ExamScreens
import com.feature.navigation.note.NoteScreens
import com.feature.navigation.note.navigateToNotes
import com.feature.navigation.note.noteNavGraph
import com.feature.navigation.subject.navigateToSubjects
import com.feature.navigation.timetable.TimetableScreens
import com.home.home.HomeComponent

const val homeGraphRoute = "home_graph"

fun NavGraphBuilder.homeGraph(navController: NavController) {
    navigation(route = homeGraphRoute, startDestination = HomeScreens.HOME.route) {
        composable(route = HomeScreens.HOME.route) {
            HomeComponent(
                screenList = HomeMainScreens.values().map { it.label },
                onNavigateToNote = { noteId ->
                    navController.navigate(NoteScreens.NOTE_EDITION.route + "/${noteId}")
                },
                onNavigateToNoteGraph = { noteNavGraph(navController) },
                onLogout = {},
                navigateToScreen = { screenName ->
                    when (HomeMainScreens.values().firstOrNull { it.label == screenName }) {
                        HomeMainScreens.SUBJECT -> navController.navigateToSubjects()
                        HomeMainScreens.NOTE -> navController.navigateToNotes()
                        HomeMainScreens.EXAMS -> navController.navigate(ExamScreens.EXAM.route)
                        HomeMainScreens.TIMETABLE -> navController.navigate(TimetableScreens.TIMETABLE.route)
                        else -> {}
                    }
                }
            )
        }
    }
}

fun NavController.navigateToHome() {
    navigate(homeGraphRoute) {
        popUpTo(currentBackStackEntry?.destination?.route ?: "") {
            inclusive = true
        }
    }
}