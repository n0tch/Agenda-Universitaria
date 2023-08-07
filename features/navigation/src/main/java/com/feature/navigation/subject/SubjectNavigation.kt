package com.feature.navigation.subject

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.feature.navigation.note.NoteScreens
import com.feature.navigation.note.navigateToNoteWithResult
import com.features.subject.SubjectComponent
import com.features.subject.detail.SubjectDetailComponent
import com.features.subject.exam.ExamComponent
import com.features.subject.exam.detail.ExamDetailComponent
import com.features.subject.timetable.newentry.NewTimeTableContent
import com.features.subject.timetable.TimetableScreen

const val subjectGraphRoute = "subject_graph"

fun NavGraphBuilder.subjectGraph(navController: NavController) {

    navigation(route = subjectGraphRoute, startDestination = SubjectScreens.SUBJECTS.route) {
        composable(route = SubjectScreens.SUBJECTS.route) {
            SubjectComponent(
                navController = navController,
                navigateToSubjectDetail = { name, id ->
                    navController.navigate(SubjectScreens.SUBJECT_DETAIL.route + "/${name}/${id}")
                }
            )
        }

        composable(route = SubjectScreens.SUBJECT_DETAIL.route + "/{name}/{id}") { backStackEntry ->
            val subjectName = backStackEntry.arguments?.getString("name") ?: ""
            val subjectId = backStackEntry.arguments?.getString("id") ?: ""
            SubjectDetailComponent(
                onBackPressed = { navController.popBackStack() },
                subjectName = subjectName,
                subjectId = subjectId,
                onNavigateToNote = { noteId -> navController.navigate(NoteScreens.NOTE.route + "/$noteId") }
            )
        }

        composable(route = SubjectScreens.EXAM.route) {
            ExamComponent(
                onBackClicked = { navController.popBackStack() },
                navigateToExam = { examId -> navController.navigate(SubjectScreens.EXAM_DETAIL.route + "/$examId") }
            )
        }

        composable(route = SubjectScreens.EXAM_DETAIL.route + "/{id}") { backStackEntry ->
            val examId = backStackEntry.arguments?.getString("id") ?: ""
            val resultScreen = navController.currentBackStackEntry?.savedStateHandle?.getLiveData<List<String>>("note_list", emptyList())
            ExamDetailComponent(
                examId = examId,
                onBackPressed = { navController.popBackStack() },
                navigateToNotesWithResult = {
                    navController.navigateToNoteWithResult()
                },
                notesSelectionLiveDat = resultScreen
            )
        }

        composable(route = SubjectScreens.TIMETABLE.route) {
            TimetableScreen(
                onBack = { navController.popBackStack() },
                onNewTimetable = { navController.navigateNewToTimetable() }
            )
        }

        composable(route = SubjectScreens.NEW_TIMETABLE.route) {
            NewTimeTableContent()
        }
    }
}

fun NavController.navigateToSubjects() {
    navigate(subjectGraphRoute)
}

fun NavController.navigateToTimetable() {
    navigate(SubjectScreens.TIMETABLE.route)
}

fun NavController.navigateNewToTimetable() {
    navigate(SubjectScreens.NEW_TIMETABLE.route)
}