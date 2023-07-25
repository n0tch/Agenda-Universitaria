package com.feature.navigation.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.features.login.LoginScreenComponent

const val loginGraphRoute = "login_graph"

fun NavGraphBuilder.loginGraph(navHostController: NavHostController) {
    navigation(route = loginGraphRoute, startDestination = LoginScreens.LOGIN.route) {
        composable(route = LoginScreens.LOGIN.route) {
            LoginScreenComponent(navHostController = navHostController, onLoginFinish = {  })
        }

//        composable(route = LoginScreens.FORGOT_PASS.route) {
//            ForgotPassComponent(navHostController = navHostController)
//        }
//
//        composable(route = LoginScreens.REGISTER.route){
//            RegisterComponent(navHostController = navHostController)
//        }
    }
}

fun NavController.navigateToLogin(){
    navigate(loginGraphRoute){
        popUpTo(currentBackStackEntry?.destination?.route ?: ""){
            inclusive = true
        }
    }
}
