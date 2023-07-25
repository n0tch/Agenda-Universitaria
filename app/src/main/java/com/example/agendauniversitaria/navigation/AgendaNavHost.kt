package com.example.agendauniversitaria.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.agendauniversitaria.feature.navigation.homeGraph
import com.example.agendauniversitaria.feature.navigation.loginGraph
import com.example.agendauniversitaria.feature.navigation.noteNavGraph
import com.feature.navigation.login.loginGraph
import com.feature.navigation.login.loginGraphRoute

@Composable
fun AgendaNavHost(startDestination: String = loginGraphRoute) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
//        loginGraph(navController)
        loginGraph(navController)
        homeGraph(navController)
        noteNavGraph(navController)
    }
}

fun NavController.navigateToHome(){
    navigate(homeGraph) {
        popUpTo(currentBackStackEntry?.destination?.route ?: "") {
            inclusive = true
        }
    }
}

fun NavController.navigateToLogin(){
    navigate(loginGraph){
        popUpTo(currentBackStackEntry?.destination?.route ?: ""){
            inclusive = true
        }
    }
}