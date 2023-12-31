package com.core.designsystem.components.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.layoutId
import java.time.LocalTime

@Composable
fun ScheduleComponent() {
    LazyColumn {
        items((0..24).toList()) {
            EventTimeItem(
                hour = LocalTime.MIN.plusHours(it.toLong()),
                { Text("A", Modifier.background(Color.DarkGray)) },
                { Text("B") }
            )
        }
    }
}

@Preview
@Composable
fun ScheduleComponentPreview() {
    ScheduleComponent()
}
