package com.feature.navigation.subject

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.feature.navigation.note.navigateToNotesWithItem
import com.features.subject.SubjectComponent
import com.features.subject.detail.SubjectDetailComponent
import com.features.subject.exam.ExamComponent

const val subjectGraphRoute = "subject_graph"

fun NavGraphBuilder.subjectGraph(navController: NavController) {

    navigation(route = subjectGraphRoute, startDestination = SubjectScreens.SUBJECTS.route) {
        composable(route = SubjectScreens.SUBJECTS.route) {
            SubjectComponent(
                navController = navController,
                navigateToSubjectDetail = {
                    navController.navigate(SubjectScreens.SUBJECT_DETAIL.route + "/$it")
                }
            )
        }

        composable(route = SubjectScreens.SUBJECT_DETAIL.route + "/{name}") { backStackEntry ->
            val subjectName = backStackEntry.arguments?.getString("name") ?: ""
            SubjectDetailComponent(
                onBackPressed = { navController.popBackStack() },
                subjectName = subjectName,
                onNavigateToNote = { note -> navController.navigateToNotesWithItem(note) }
            )
        }

        composable(route = SubjectScreens.EXAM.route){
            ExamComponent()
        }
    }
}

fun NavController.navigateToSubjects() {
    navigate(SubjectScreens.SUBJECTS.route)
}
