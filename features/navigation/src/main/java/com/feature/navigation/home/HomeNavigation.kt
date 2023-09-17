package com.feature.navigation.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.feature.navigation.HomeMainScreens
import com.feature.navigation.exam.navigateToExam
import com.feature.navigation.exam.navigateToExams
import com.feature.navigation.navigateTo
import com.feature.navigation.note.navigateToNotes
import com.home.home.HomeComponent
import com.home.home.navigation.HomeNavigation

const val homeGraphRoute = "home_graph"

fun NavGraphBuilder.homeGraph(navController: NavController) {
    navigation(route = homeGraphRoute, startDestination = HomeScreens.HOME.route) {
        composable(route = HomeScreens.HOME.route) {
            HomeComponent(
                screenList = HomeMainScreens.values().map { it.label },
                onNavigation = {navigation ->
                    when(navigation){
                        is HomeNavigation.NavigateToExamById -> navController.navigateToExam(navigation.examId)
                        is HomeNavigation.NavigateToNoteById -> TODO()
                        HomeNavigation.NavigateToExams -> navController.navigateToExams()
                        HomeNavigation.NavigateToNotes -> navController.navigateToNotes()
                        is HomeNavigation.NavigateToScreenByName -> navController.navigateTo(navigation.screenName)
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
//        launchSingleTop = true
        restoreState = true
    }
}