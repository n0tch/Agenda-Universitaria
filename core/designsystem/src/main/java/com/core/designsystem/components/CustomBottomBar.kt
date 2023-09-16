package com.core.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun <T> CustomBottomBar(
    items: List<T> = emptyList(),
    selected: T,
    content: @Composable (T) -> Unit,
    selectedContent: @Composable (T) -> Unit,
    onItemSelected: (T) -> Unit
) {
    Row(
        modifier = Modifier
            .height(68.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
            .background(MaterialTheme.colorScheme.inversePrimary)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEach {
            Box(modifier = Modifier.clickableNoRipple { onItemSelected(it) }){
                if (it == selected)
                    selectedContent(it)
                else
                    content(it)
            }
        }
    }
}

@Preview
@Composable
fun CustomBottomBarPreview() {
    CustomBottomBar(
        items = listOf(1, 2, 3, 4),
        selected = 1,
        content = { Text(it.toString()) },
        selectedContent = { Text(it.toString()) },
        onItemSelected = {})
}