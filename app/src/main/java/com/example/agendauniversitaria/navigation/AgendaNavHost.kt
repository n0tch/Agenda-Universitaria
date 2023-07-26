package com.example.agendauniversitaria.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.feature.navigation.authentication.login.loginGraph
import com.feature.navigation.authentication.login.loginGraphRoute
import com.feature.navigation.home.homeGraph
import com.feature.navigation.home.homeGraphRoute
import com.feature.navigation.note.noteNavGraph

@Composable
fun AgendaNavHost(startDestination: String = loginGraphRoute) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        loginGraph(navController)
        homeGraph(navController)
        noteNavGraph(navController)
    }
}

fun NavController.navigateToHome(){
    navigate(homeGraphRoute) {
        popUpTo(currentBackStackEntry?.destination?.route ?: "") {
            inclusive = true
        }
    }
}

fun NavController.navigateToLogin(){
    navigate(loginGraphRoute){
        popUpTo(currentBackStackEntry?.destination?.route ?: ""){
            inclusive = true
        }
    }
}