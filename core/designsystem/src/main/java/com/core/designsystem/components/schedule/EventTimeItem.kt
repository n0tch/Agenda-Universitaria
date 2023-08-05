package com.core.designsystem.components.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.LocalTime
import java.time.format.DateTimeFormatter

private val hourFormatter = DateTimeFormatter.ofPattern("h a")

@Composable
fun EventTimeItem(
    hour: LocalTime,
    vararg contents: @Composable () -> Unit
) {
    Row(
        modifier = Modifier
            .height(58.dp)
            .background(Color.White)
    ) {
        Text(
            modifier = Modifier
                .width(58.dp)
                .fillMaxHeight(),
            text = hour.format(hourFormatter),
            textAlign = TextAlign.Center
        )

        Box(modifier = Modifier
            .background(Color.Gray)
            .fillMaxHeight()
            .fillMaxSize()
            .drawBehind {
                val y = size.height
                drawLine(Color.Black, Offset(0f, y), Offset(size.width, y))
            }
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxSize()
            ) {
                contents.forEach { it.invoke() }
            }
        }
    }
}

@Preview
@Composable
fun EventTimeItemPreview() {
    EventTimeItem(
        hour = LocalTime.NOON,
        {
            Text(
                text = "abc", modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color.Green)
            )
        },
        {
            Text(
                text = "Abccc",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            )
        })
}