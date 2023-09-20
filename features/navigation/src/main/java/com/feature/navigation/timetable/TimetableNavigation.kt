package com.feature.navigation.timetable

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.feature.timetable.TimetableScreen
import com.feature.timetable.newentry.NewTimeTableContent

fun NavGraphBuilder.timetableGraph(navController: NavController) {
    composable(route = TimetableScreens.TIMETABLE.route) {
        TimetableScreen(
            onBack = navController::popBackStack,
            onNewTimetable = navController::navigateNewToTimetable
        )
    }

    composable(route = TimetableScreens.NEW_TIMETABLE.route) {
        NewTimeTableContent(
            onBackPressed = navController::popBackStack
        )
    }
}

fun NavController.navigateNewToTimetable() {
    navigate(TimetableScreens.NEW_TIMETABLE.route){
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavController.navigateToTimetable() {
    navigate(TimetableScreens.TIMETABLE.route){
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
