package com.feature.navigation.subject

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import com.feature.navigation.event.navigateToEditEventWithSubject
import com.feature.navigation.note.NoteScreens
import com.features.subject.detail.SubjectDetailComponent
import com.features.subject.edit.SubjectEditComponent
import com.features.subject.list.SubjectComponent

const val subjectGraphRoute = "subject_graph"

enum class SubjectRoutes(val route: String) {
    DETAIL(SubjectScreens.SUBJECT_DETAIL.route + "/{id}")
}

fun NavGraphBuilder.subjectGraph(navController: NavController) {

    navigation(route = subjectGraphRoute, startDestination = SubjectScreens.SUBJECTS.route) {
        composable(route = SubjectScreens.SUBJECTS.route) {
            SubjectComponent(
                onBackPressed = { navController.popBackStack() },
                navigateToSubjectDetail = { name, id ->
                    navController.navigate(SubjectScreens.SUBJECT_DETAIL.route + "/${id}")
                },
                navigateToSubjectEdit = { navController.navigateToEditSubject() }
            )
        }

        composable(
            route = SubjectRoutes.DETAIL.route,
            deepLinks = listOf(
                navDeepLink { uriPattern = SubjectRoutes.DETAIL.route }
            )
        ) { backStackEntry ->
            val subjectId = backStackEntry.arguments?.getString("id")
            SubjectDetailComponent(
                onBackPressed = { navController.popBackStack() },
                subjectId = subjectId?.toIntOrNull() ?: -1,
                onNavigateToNote = { noteId -> navController.navigate(NoteScreens.NOTE_EDITION.route + "/$noteId") },
                navigateToAddEvent = {
                    navController.navigateToEditEventWithSubject(it)
                }
            )
        }
    }

    composable(route = SubjectScreens.ADD.route){
        SubjectEditComponent()
    }
}

fun NavController.navigateToSubjects() {
    navigate(subjectGraphRoute) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavController.navigateToSubjectById(subjectId: Int) {
    navigate(SubjectScreens.SUBJECT_DETAIL.route + "/$subjectId") {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavController.navigateToEditSubject(){
    navigate(SubjectScreens.ADD.route)
}
