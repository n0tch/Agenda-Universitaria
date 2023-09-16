package com.feature.navigation

import androidx.navigation.NavController
import com.feature.navigation.exam.navigateToExams
import com.feature.navigation.note.navigateToNotes
import com.feature.navigation.subject.navigateToSubjects
import com.feature.navigation.timetable.navigateToTimetable

fun NavController.navigateTo(screenName: String){
    when(screenName){
        HomeMainScreens.SUBJECT.label -> navigateToSubjects()
        HomeMainScreens.NOTE.label -> navigateToNotes()
        HomeMainScreens.EXAMS.label -> navigateToExams()
        HomeMainScreens.TIMETABLE.label -> navigateToTimetable()
        HomeMainScreens.HOMEWORK.label -> {}
        HomeMainScreens.NOTIFICATIONS.label -> {}
        else  -> {}
    }
}