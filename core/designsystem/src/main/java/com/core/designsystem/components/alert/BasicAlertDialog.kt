package com.core.designsystem.components.alert

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog

@Composable
fun BasicAlertDialog(
    onDismiss: () -> Unit,
    content: @Composable () -> Unit
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        content()
    }
}