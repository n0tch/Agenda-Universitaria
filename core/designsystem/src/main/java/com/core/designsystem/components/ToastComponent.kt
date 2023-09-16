package com.core.designsystem.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun ToastComponent(message: String) {
    Toast.makeText(
        LocalContext.current.applicationContext,
        message,
        Toast.LENGTH_SHORT
    ).show()
}