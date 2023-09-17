package com.feature.navigation.exam

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.feature.exam.detail.ExamDetailComponent
import com.feature.exam.list.ExamComponent

const val examGraphRoute = "exam_graph"

fun NavGraphBuilder.examGraph(navController: NavController) {
    navigation(route = examGraphRoute, startDestination = ExamScreens.EXAM.route) {
        composable(route = ExamScreens.EXAM.route) {
            ExamComponent(
                onBackClicked = { navController.popBackStack() },
                navigateToExam = { examId -> navController.navigate(ExamScreens.EXAM_DETAIL.route + "/$examId") }
            )
        }
    }

    composable(route = ExamScreens.EXAM_DETAIL.route + "/{id}") { backStackEntry ->
        val examId = backStackEntry.arguments?.getString("id") ?: ""
        val resultScreen =
            navController.currentBackStackEntry?.savedStateHandle?.getLiveData<List<String>>(
                "note_list",
                emptyList()
            )
        ExamDetailComponent(
            examId = examId,
            onBackPressed = { navController.popBackStack() },
            navigateToNotesWithResult = {},
            notesSelectionLiveDat = resultScreen
        )
    }
}

fun NavController.navigateToExams() {
    navigate(examGraphRoute) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavController.navigateToExam(examId: Int){
    navigate(ExamScreens.EXAM_DETAIL.route + "/$examId") {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
