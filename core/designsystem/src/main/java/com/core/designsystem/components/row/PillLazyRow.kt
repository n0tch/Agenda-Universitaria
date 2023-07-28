package com.core.designsystem.components.row

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PillLazyRow(pillList: List<String>) {
    LazyRow(modifier = Modifier.padding(start = 8.dp)) {
        items(pillList) {
            PillItem(it, Color.Gray.copy(alpha = .4f))
        }
    }
}

@Composable
fun PillItem(name: String, color: Color) {
    Box(
        modifier = Modifier
            .padding(2.dp)
            .background(color, RoundedCornerShape(6.dp))
    ) {
        Text(
            text = name,
            modifier = Modifier.padding(8.dp),
            fontSize = MaterialTheme.typography.bodySmall.fontSize
        )
    }
}