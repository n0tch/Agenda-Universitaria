package com.example.agendauniversitaria.feature.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.agendauniversitaria.common.ShowToast
import com.example.agendauniversitaria.feature.navigation.LoginScreens.FORGOT_PASS
import com.example.agendauniversitaria.feature.navigation.LoginScreens.REGISTER
import com.example.agendauniversitaria.navigation.navigateToHome

@Composable
fun LoginScreenComponent(navHostController: NavHostController) {
    val viewModel: LoginViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var isLoading by remember { mutableStateOf(false) }

    when (uiState) {
        LoginState.LoginIdle -> {}
        LoginState.LoginLoading -> isLoading = true
        is LoginState.LoginFail -> {
            ShowToast((uiState as LoginState.LoginFail).exception.message.toString())
            isLoading = false
        }

        is LoginState.LoginSuccess -> {
            viewModel.saveUser((uiState as LoginState.LoginSuccess).user)
        }

        LoginState.LoginFinish -> {
            LaunchedEffect(key1 = Unit, block = {
                navHostController.navigateToHome()
            })
        }
    }

    LoginScreen(
        isLoading = isLoading,
        onForgotPass = {
            navHostController.navigate(FORGOT_PASS.route)
        }, onLogin = { email, password ->
            viewModel.loginWithCredentials(email, password)
        },
        onLoginWithSocial = {
            viewModel.loginWithSocial(it)
        },
        onRegisterClicked = {
            navHostController.navigate(REGISTER.route)
        }
    )
}
