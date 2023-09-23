package com.feature.navigation.calendar

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.feature.calendar.CalendarComponent

enum class CalendarScreens(val route: String){
    DETAIL("calendar_detail")
}

fun NavGraphBuilder.calendarGraph(navController: NavController){
    composable(route = CalendarScreens.DETAIL.route){
        CalendarComponent()
    }
}

fun NavController.navigateToCalendar(){
    navigate(CalendarScreens.DETAIL.route)
}
