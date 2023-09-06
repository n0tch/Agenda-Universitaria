package com.core.designsystem.components.row

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun <T>PillLazyRow(
    pillList: List<T> = emptyList(),
    content: @Composable (T) -> Unit = {},
    onClick: (T) -> Unit = {}
) {
    LazyRow(modifier = Modifier.padding(start = 8.dp)) {
        items(pillList) {
//            PillItem(it, Color.Gray.copy(alpha = .4f))
            Box(Modifier.clickable { onClick(it) }) {
                content(it)
            }
        }
        item{
            IconButton(onClick = {}){
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Label")
            }
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

@Preview
@Composable
fun PillLazyRowPreview() {
    PillLazyRow(listOf("1", "2", "3"), content = {
        PillItem(it, Color.Yellow)
    }, onClick = {})
}