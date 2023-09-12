package com.feature.navigation.exam

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.feature.exam.detail.ExamDetailComponent
import com.feature.exam.list.ExamComponent

fun NavGraphBuilder.examGraph(navController: NavController){
    composable(route = ExamScreens.EXAM.route) {
        ExamComponent(
            onBackClicked = { navController.popBackStack() },
            navigateToExam = { examId -> navController.navigate(ExamScreens.EXAM_DETAIL.route + "/$examId") }
        )
    }

    composable(route = ExamScreens.EXAM_DETAIL.route + "/{id}") { backStackEntry ->
        val examId = backStackEntry.arguments?.getString("id") ?: ""
        val resultScreen = navController.currentBackStackEntry?.savedStateHandle?.getLiveData<List<String>>("note_list", emptyList())
        ExamDetailComponent(
            examId = examId,
            onBackPressed = { navController.popBackStack() },
            navigateToNotesWithResult = {},
            notesSelectionLiveDat = resultScreen
        )
    }
}
