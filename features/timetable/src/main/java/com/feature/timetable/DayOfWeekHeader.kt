package com.feature.timetable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.Locale
import kotlin.random.Random

@Composable
fun DayOfWeekHeader(
    weekDays: Array<DayOfWeek> = DayOfWeek.values(),
    onClick: (String) -> Unit = {}
) {

    Column(modifier = Modifier.fillMaxWidth()) {

        LazyRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            items(weekDays) {
                DayCard(
                    modifier = Modifier.padding(horizontal = 2.dp),
                    isSelected = false,
                    text = it.getDisplayName(TextStyle.FULL, Locale.getDefault()),
                    indicatorCount = Random.nextInt(4),
                    onClick = {
                        onClick(it)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun DayOfWeekHeaderPreview() {
    DayOfWeekHeader()
}