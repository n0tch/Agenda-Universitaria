package com.feature.navigation.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.feature.navigation.HomeMainScreens
import com.feature.navigation.navigateTo
import com.feature.navigation.subject.navigateToSubjectById
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
        popUpTo(HomeScreens.HOME.route) {
            saveState = true
        }
//        launchSingleTop = true
        restoreState = true
    }
}