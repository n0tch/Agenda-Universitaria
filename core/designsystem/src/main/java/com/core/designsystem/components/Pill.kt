package com.core.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Pill(text: String, color: Color) {
    Box(
        modifier = Modifier
            .padding(2.dp)
            .background(color, RoundedCornerShape(25.dp))
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(4.dp),
            fontSize = MaterialTheme.typography.bodySmall.fontSize
        )
    }
}