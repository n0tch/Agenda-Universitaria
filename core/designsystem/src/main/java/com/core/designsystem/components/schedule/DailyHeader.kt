package com.core.designsystem.components.schedule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DailyHeader(modifier: Modifier = Modifier, text: String) {
    Column(
        modifier = modifier
            .height(84.dp)
            .width(84.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = text)
        Divider()
    }
}

@Preview
@Composable
fun DailyHeaderPreview() {
    DailyHeader(text = "12:00 AM")
}