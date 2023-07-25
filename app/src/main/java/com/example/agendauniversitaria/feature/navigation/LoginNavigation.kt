package com.example.agendauniversitaria.feature.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.agendauniversitaria.feature.forgotpass.ForgotPassComponent
import com.example.agendauniversitaria.feature.login.LoginScreenComponent
import com.example.agendauniversitaria.feature.navigation.LoginScreens.*
import com.example.agendauniversitaria.feature.register.RegisterComponent

const val loginGraph = "login_graph"

internal enum class LoginScreens(val route: String) {
    LOGIN("login_route"),
    FORGOT_PASS("forgot_pass_route"),
    REGISTER("register_route")
}

fun NavGraphBuilder.loginGraph(navHostController: NavHostController) {
    navigation(route = loginGraph, startDestination = LOGIN.route) {
        composable(route = LOGIN.route) {
            LoginScreenComponent(navHostController = navHostController)
        }

        composable(route = FORGOT_PASS.route) {
            ForgotPassComponent(navHostController = navHostController)
        }

        composable(route = REGISTER.route){
            RegisterComponent(navHostController = navHostController)
        }
    }
}
