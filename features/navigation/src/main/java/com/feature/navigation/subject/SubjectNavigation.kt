package com.feature.navigation.subject

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.feature.navigation.note.navigateToNotesWithItem
import com.features.subject.SubjectComponent
import com.features.subject.detail.SubjectDetailComponent
import com.features.subject.exam.ExamComponent
import com.features.subject.timetable.newentry.NewTimeTableContent
import com.features.subject.timetable.TimetableScreen

const val subjectGraphRoute = "subject_graph"

fun NavGraphBuilder.subjectGraph(navController: NavController) {

    navigation(route = subjectGraphRoute, startDestination = SubjectScreens.SUBJECTS.route) {
        composable(route = SubjectScreens.SUBJECTS.route) {
            SubjectComponent(
                navController = navController,
                navigateToSubjectDetail = {
                    navController.navigate(SubjectScreens.SUBJECT_DETAIL.route + "/${it.name}/${it.id}")
                }
            )
        }

        composable(route = SubjectScreens.SUBJECT_DETAIL.route + "/{name}/{id}") { backStackEntry ->
            val subjectName = backStackEntry.arguments?.getString("name") ?: ""
            val subjectId= backStackEntry.arguments?.getString("id") ?: ""
            SubjectDetailComponent(
                onBackPressed = { navController.popBackStack() },
                subjectName = subjectName,
                subjectId = subjectId,
                onNavigateToNote = { note -> navController.navigateToNotesWithItem(note) }
            )
        }

        composable(route = SubjectScreens.EXAM.route){
            ExamComponent()
        }

        composable(route = SubjectScreens.TIMETABLE.route){
            TimetableScreen(
                onBack = { navController.popBackStack() },
                onNewTimetable = { navController.navigateNewToTimetable() }
            )
        }

        composable(route = SubjectScreens.NEW_TIMETABLE.route){
            NewTimeTableContent()
        }
    }
}

fun NavController.navigateToSubjects() {
    navigate(subjectGraphRoute)
}

fun NavController.navigateToTimetable(){
    navigate(SubjectScreens.TIMETABLE.route)
}

fun NavController.navigateNewToTimetable(){
    navigate(SubjectScreens.NEW_TIMETABLE.route)
}