package com.feature.timetable

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.model.TimetableEntry
import com.example.model.getEntryByWeekDayName

@Composable
fun TimeTableComponent(
    modifier: Modifier = Modifier,
    timetableEntries: List<TimetableEntry> = emptyList(),

    ) {
    var weekDaySelected by remember { mutableStateOf("Segunda") }
    var dailyEvents: List<TimetableEntry> by rememberSaveable(Unit) { mutableStateOf(listOf()) }

    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
        DayOfWeekHeader(onClick = {
            weekDaySelected = it
            dailyEvents = timetableEntries.getEntryByWeekDayName(it)
        })

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = weekDaySelected,
            textAlign = TextAlign.Center
        )

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(dailyEvents) {
                ScheduleCard(it)
            }
        }
    }

//    Row(modifier = modifier.fillMaxSize()) {
//        LazyColumn(
//            Modifier
//                .fillMaxHeight()
//                .background(Color.Gray),
//            contentPadding = PaddingValues(2.dp)
//        ) {
//            itemsIndexed(weekDays) { index, item ->
//                DayCard(
//                    modifier = modifier.padding(horizontal = 2.dp, vertical = 4.dp),
//                    isSelected = index == selectedDayIndex,
//                    text = item.getDisplayName(TextStyle.FULL, Locale.getDefault()),
//                    hasIndicator = true
//                ) { selectedDay ->
//                    selectedDayIndex = index
//                    weekDaySelected = selectedDay
//                    dailyEvents = timetableEntries.getEntryByWeekDayName(selectedDay)
//                }
//            }
//        }
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//
//            Box(
//                Modifier
//                    .fillMaxWidth()
//                    .fillMaxHeight(.2f)
//                    .background(MaterialTheme.colorScheme.onSurfaceVariant),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(
//                    text = weekDaySelected,
//                    fontStyle = MaterialTheme.typography.titleLarge.fontStyle
//                )
//            }
//
//            LazyColumn(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .fillMaxHeight(1f)
//            ) {
//                items(dailyEvents) {
//                    ScheduleCard(it)
//                }
//            }
//        }
//    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun WeaklySubjectsComponentPreview() {
    TimeTableComponent()
}