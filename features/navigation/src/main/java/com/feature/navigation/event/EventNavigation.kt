package com.feature.navigation.event

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.features.event.edit.EditEventComponent
import com.features.event.edit.EditEventNavigation
import com.features.event.list.ListEventComponent

fun NavGraphBuilder.eventGraph(navController: NavController) {

    composable(route = EventScreens.DETAIL.route) {
        EditEventComponent(onNavigation = {
            when(it){
                EditEventNavigation.OnBack -> navController.popBackStack()
            }
        })
    }

    composable(route = EventScreens.DETAIL_WITH_SUBJECT.route + "/{subjectId}") {
        val subject = it.arguments?.getString("subjectId") ?: "0"
        EditEventComponent(
            subject=subject.toInt(),
            onNavigation = {
                when(it){
                    EditEventNavigation.OnBack -> navController.popBackStack()
                }
            }
        )
    }

    composable(route = EventScreens.LIST.route) {
        ListEventComponent()
    }
}

fun NavController.navigateToEditEvent(){
    navigate(EventScreens.DETAIL.route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavController.navigateToEditEventWithSubject(subjectId: Int){
    navigate(EventScreens.DETAIL_WITH_SUBJECT.route + "/$subjectId")
}

fun NavController.navigateToListEvent(){
    navigate(EventScreens.LIST.route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
