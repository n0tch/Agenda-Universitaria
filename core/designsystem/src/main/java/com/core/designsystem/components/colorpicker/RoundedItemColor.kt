package com.core.designsystem.components.colorpicker

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RoundedItemColor(
    color: Color,
    isSelected: Boolean = false,
    onColorSelected: (Color) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .aspectRatio(1F)
            .background(color, CircleShape)
            .clickable { onColorSelected(color) },
        contentAlignment = Alignment.Center
    ){
        if(isSelected){
            Icon(modifier = Modifier.fillMaxSize(), imageVector = Icons.Filled.Check, contentDescription = "")
        }
    }
}

@Preview
@Composable
fun ColorItemPreview() {
    RoundedItemColor(Color.Gray)
}

@Preview
@Composable
fun ColorItemSelectedPreview() {
    RoundedItemColor(Color.Gray, isSelected = true)
}