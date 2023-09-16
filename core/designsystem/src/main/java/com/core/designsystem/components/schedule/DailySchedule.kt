package com.core.designsystem.components.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

const val hourHeight = 84

@Composable
fun DailySchedule(
    hours: List<Int>
) {
    val events = listOf(
        Event(title = "Evento 1", start = 1, end = 2.5f),
        Event(title = "Evento 2", start = 3, end = 4f),
        Event(title = "Evento 3", start = 5, end = 10.7f),
        Event(title = "Evento 3", start = 14, end = 16f),
        Event(title = "asdasd", start = 1, end = 2F),
        Event(title = "asdasd", start = 1, end = 2F),
        Event(title = "asdasd", start = 1, end = 2F),
    )
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        Row {
            Column {
                hours.forEach { DailyHeader(text = it.toString()) }
            }

            MyColumn(events) {
                events.forEach {
                    Text(
                        text = it.title, modifier = Modifier
                            .height((hourHeight * (it.end - it.start)).dp)
                            .width(64.dp)
                            .background(Color.White)
                            .border(1.dp, Color.Blue)
                    )
                }
            }
        }
    }
}

data class Event(val title: String, val start: Int, val end: Float)

@Composable
fun MyColumn(
    events: List<Event>,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(modifier = modifier, content = { content() }) { measurables, constraints ->
        val placedItems = mutableListOf<IntRange>()

        val placeables = measurables.map { measurable ->
            // Measure each children
            measurable.measure(constraints)
        }

        // Set the size of the layout as big as it can
        layout(constraints.maxWidth, constraints.maxHeight) {
            // Track the y co-ord we have placed children up to

            // Place children in the parent layout
            placeables.forEachIndexed { index, placeable ->
                // Position item on the screen
                val ypos = ((events[index].start - 1) * hourHeight).dp.roundToPx()

                val xOffset = placedItems.count { it.contains(events[index].start) }
                placedItems.add(events[index].start..events[index].end.toInt())

                placeable.placeRelative(x = xOffset * placeable.width, y = ypos)
            }
        }
    }
}

@Preview
@Composable
fun DailySchedulePreview() {
    DailySchedule(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16))
}