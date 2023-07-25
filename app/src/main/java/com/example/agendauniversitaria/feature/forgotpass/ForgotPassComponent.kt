package com.example.agendauniversitaria.feature.forgotpass

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.agendauniversitaria.common.ShowToast

@Composable
fun ForgotPassComponent(navHostController: NavHostController) {
    val viewModel: ForgotPassViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var openDialog by remember { mutableStateOf(false) }

    when (uiState) {
        is ForgotPassState.Error ->
            ShowToast((uiState as ForgotPassState.Error).exception.message.toString())
        ForgotPassState.Idle -> {}
        ForgotPassState.Loading -> {}
        is ForgotPassState.Success -> {
            openDialog = true
            //navHostController.popBackStack()
        }
    }

    ForgotPassScreen(
        onRecoverClicked = {
            viewModel.resetPassword(it)
        },
        openAlert = openDialog
    )
}