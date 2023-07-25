package com.features.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.core.designsystem.components.ToastComponent

@Composable
fun LoginScreenComponent(
    navHostController: NavHostController,
    onLoginFinish: () -> Unit
) {
    val viewModel: LoginViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var isLoading by remember { mutableStateOf(false) }

    when (uiState) {
        LoginState.LoginIdle -> {}
        LoginState.LoginLoading -> isLoading = true
        is LoginState.LoginFail -> {
            ToastComponent((uiState as LoginState.LoginFail).exception.message.toString())
            isLoading = false
        }

        LoginState.LoginFinish -> {
            LaunchedEffect(key1 = Unit, block = {
//                navHostController.navigateToHome()
                onLoginFinish()
            })
        }
    }

    LoginScreen(
        isLoading = isLoading,
        onForgotPass = {
//            navHostController.navigate(FORGOT_PASS.route)
        }, onLogin = { email, password ->
            viewModel.loginWithCredentials(email, password)
        },
        onLoginWithSocial = {
            viewModel.loginWithSocial(it)
        },
        onRegisterClicked = {
//            navHostController.navigate(REGISTER.route)
        }
    )
}
