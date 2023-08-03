package com.core.designsystem.components.alert

import androidx.compose.foundation.background
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicAlertDialog(
    onDismiss: () -> Unit,
    content: @Composable () -> Unit
) {
    AlertDialog(
        modifier = Modifier.background(MaterialTheme.colorScheme.inverseOnSurface),
        onDismissRequest = { onDismiss() }) {
        content()
    }
}