package com.example.agendauniversitaria.feature.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.agendauniversitaria.feature.home.HomeComponent

const val homeGraph = "home_graph"

internal enum class HomeScreens(val route: String) {
    HOME("home_route")
}

fun NavGraphBuilder.homeGraph(navController: NavController){
    navigation(route = homeGraph, startDestination = HomeScreens.HOME.route){
        composable(route = HomeScreens.HOME.route){
            HomeComponent(navController)
        }
    }
}