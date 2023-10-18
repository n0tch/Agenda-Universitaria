package com.feature.navigation.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.feature.navigation.HomeMainScreens
import com.feature.navigation.calendar.navigateToCalendar
import com.feature.navigation.event.navigateToListEvent
import com.feature.navigation.navigateTo
import com.feature.navigation.note.navigateToNoteDetail
import com.feature.navigation.note.navigateToNotes
import com.feature.navigation.subject.navigateToSubjectById
import com.home.list.HomeComponent
import com.home.list.navigation.HomeNavigation

const val homeGraphRoute = "home_graph"

fun NavGraphBuilder.homeGraph(navController: NavController) {
    navigation(route = homeGraphRoute, startDestination = HomeScreens.HOME.route) {
        composable(route = HomeScreens.HOME.route) {
            HomeComponent(
                screenList = HomeMainScreens.values().map { it.label },
                onNavigation = { navigation ->
                    when(navigation){
                        is HomeNavigation.NavigateToEventById -> {}
                        is HomeNavigation.NavigateToNoteById ->
                            navController.navigateToNoteDetail(navigation.noteId)
                        HomeNavigation.NavigateToEvents ->
                            navController.navigateToListEvent()
                        HomeNavigation.NavigateToNotes ->
                            navController.navigateToNotes()
                        is HomeNavigation.NavigateToScreenByName ->
                            navController.navigateTo(navigation.screenName)
                        is HomeNavigation.NavigateToSubjectById ->
                            navController.navigateToSubjectById(navigation.subjectId)
                        is HomeNavigation.NavigateToCalendar ->
                            navController.navigateToCalendar()
                        is HomeNavigation.NavigateToScreenByEnum -> {

                        }
                    }
                }
            )
        }
    }
}

fun NavController.navigateToHome() {
    navigate(homeGraphRoute) {
        popUpTo(HomeScreens.HOME.route) {
            saveState = true
        }
        restoreState = true
    }
}
