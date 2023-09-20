package com.example.agendauniversitaria.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.feature.navigation.authentication.login.loginGraph
import com.feature.navigation.authentication.login.loginGraphRoute
import com.feature.navigation.event.eventGraph
import com.feature.navigation.exam.examGraph
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
        examGraph(navController)
        eventGraph(navController)
    }
}
