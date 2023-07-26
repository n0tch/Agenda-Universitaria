package com.features.authentication.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.core.designsystem.components.ToastComponent

@Composable
fun RegisterComponent(
    navHostController: NavHostController,
    onRegisterSuccess: () -> Unit
) {

    val viewModel: RegisterViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var isLoading by remember { mutableStateOf(false) }

    when (uiState) {
        is RegisterState.RegisterFail ->
            ToastComponent((uiState as RegisterState.RegisterFail).exception.message.toString())

        RegisterState.RegisterIdle -> {}
        RegisterState.RegisterLoading -> {
            isLoading = true
        }

        is RegisterState.RegisterSuccess -> {
            LaunchedEffect(key1 = Unit, block = {
                onRegisterSuccess()
            })
        }
    }

    RegisterScreen(
        isLoading = isLoading,
        onRegisterButtonClicked = { email, password, username, profileImage ->
            viewModel.register(email, password, username, profileImage)
        },
        onBackButtonClicked = {
            navHostController.popBackStack()
        }
    )
}