package com.feature.navigation.subject

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.feature.navigation.note.NoteScreens
import com.features.subject.list.SubjectComponent
import com.features.subject.detail.SubjectDetailComponent

const val subjectGraphRoute = "subject_graph"

fun NavGraphBuilder.subjectGraph(navController: NavController) {

    navigation(route = subjectGraphRoute, startDestination = SubjectScreens.SUBJECTS.route) {
        composable(route = SubjectScreens.SUBJECTS.route) {
            SubjectComponent(
                onBackPressed = { navController.popBackStack() },
                navigateToSubjectDetail = { name, id ->
                    navController.navigate(SubjectScreens.SUBJECT_DETAIL.route + "/${name}/${id}")
                }
            )
        }

        composable(route = SubjectScreens.SUBJECT_DETAIL.route + "/{name}/{id}") { backStackEntry ->
            val subjectName = backStackEntry.arguments?.getString("name") ?: ""
            val subjectId = backStackEntry.arguments?.getString("id")
            SubjectDetailComponent(
                onBackPressed = { navController.popBackStack() },
                subjectName = subjectName,
                subjectId = subjectId?.toIntOrNull() ?: -1,
                onNavigateToNote = { noteId -> navController.navigate(NoteScreens.NOTE.route + "/$noteId") }
            )
        }
    }
}

fun NavController.navigateToSubjects() {
    navigate(subjectGraphRoute)
}
