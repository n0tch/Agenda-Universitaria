package com.features.authentication.forgotpassword

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPassScreen(
    openAlert: Boolean,
    onRecoverClicked: (String) -> Unit
) {
    var email by remember { mutableStateOf("") }
    var dialog by remember {
        mutableStateOf(false)
    }

    Column {
        TextField(value = email, onValueChange = { email = it })
        Button(onClick = {
            dialog = true
            onRecoverClicked(email)
        }) {
            Text(text = "Reset")
        }
    }

    if (dialog) {
        AlertDialog(
            onDismissRequest = { dialog = false },
            confirmButton = {
                TextButton(onClick = { dialog = false }) {
                    Text(text = "Ok")
                }
            },
            title = { Text(text = "Recuperar senha") },
            text = { Text(text = "Verifique sua caixa de email!") }
        )
    }
}