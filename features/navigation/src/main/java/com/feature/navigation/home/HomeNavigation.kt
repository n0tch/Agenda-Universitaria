package com.feature.navigation.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.feature.navigation.HomeMainScreens
import com.feature.navigation.exam.ExamScreens
import com.feature.navigation.navigateTo
import com.feature.navigation.note.NoteRoutes
import com.feature.navigation.note.NoteScreens
import com.feature.navigation.note.navigateToNotes
import com.feature.navigation.note.noteNavGraph
import com.feature.navigation.subject.navigateToSubjectById
import com.feature.navigation.subject.navigateToSubjects
import com.feature.navigation.timetable.TimetableScreens
import com.feature.navigation.timetable.navigateToTimetable
import com.home.home.HomeComponent

const val homeGraphRoute = "home_graph"

fun NavGraphBuilder.homeGraph(navController: NavController) {
    navigation(route = homeGraphRoute, startDestination = HomeScreens.HOME.route) {
        composable(route = HomeScreens.HOME.route) {
            HomeComponent(
                screenList = HomeMainScreens.values().map { it.label },
                addScreens = listOf(),
                navigateToSubject = { subjectName, subjectId ->
                    navController.navigateToSubjectById(subjectName, subjectId)
                },
                navigateToScreen = { navController.navigateTo(it) }
            )
        }
    }
}

fun NavController.navigateToHome() {
    navigate(homeGraphRoute) {
        popUpTo(HomeScreens.HOME.route/*currentBackStackEntry?.destination?.route ?: ""*/) {
            saveState = true
        }
//        launchSingleTop = true
        restoreState = true
    }
}