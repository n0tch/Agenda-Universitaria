package com.feature.navigation.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.feature.navigation.HomeMainScreens
import com.feature.navigation.note.NOTE_ITEM_ARGUMENT
import com.feature.navigation.note.navigateToNotes
import com.feature.navigation.note.navigateToNotesWithItem
import com.feature.navigation.note.noteNavGraph
import com.feature.navigation.subject.SubjectScreens
import com.feature.navigation.subject.navigateToSubjects
import com.features.subject.SubjectComponent
import com.home.home.HomeComponent

const val homeGraphRoute = "home_graph"

fun NavGraphBuilder.homeGraph(navController: NavController) {
    navigation(route = homeGraphRoute, startDestination = HomeScreens.HOME.route) {
        composable(route = HomeScreens.HOME.route) {
            HomeComponent(
                screenList = HomeMainScreens.values().map { it.label },
                onNavigateToNote = { note ->
                    navController.navigateToNotesWithItem(note)
                },
                onNavigateToNoteGraph = { noteNavGraph(navController) },
                onLogout = {},
                navigateToScreen = { screenName ->
                    when (HomeMainScreens.values().firstOrNull { it.label == screenName }) {
                        HomeMainScreens.SUBJECT -> navController.navigateToSubjects()
                        HomeMainScreens.NOTE -> navController.navigateToNotes()
                        HomeMainScreens.EXAMS -> navController.navigate(SubjectScreens.EXAM.route)
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