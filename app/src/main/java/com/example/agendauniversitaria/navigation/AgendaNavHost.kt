package com.example.agendauniversitaria.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.feature.navigation.authentication.login.loginGraph
import com.feature.navigation.calendar.calendarGraph
import com.feature.navigation.event.eventGraph
import com.feature.navigation.home.homeGraph
import com.feature.navigation.home.homeGraphRoute
import com.feature.navigation.note.noteNavGraph
import com.feature.navigation.subject.subjectGraph
import com.feature.navigation.timetable.timetableGraph

@Composable
fun AgendaNavHost(navController: NavHostController, startDestination: String = homeGraphRoute) {
    NavHost(navController = navController, startDestination = startDestination) {
        loginGraph(navController)
        homeGraph(navController)
        noteNavGraph(navController)
        subjectGraph(navController)
        timetableGraph(navController)
        eventGraph(navController)
        calendarGraph(navController)
    }
}
