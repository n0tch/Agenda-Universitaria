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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.model.Timetable
import java.time.DayOfWeek

@Composable
fun TimeTableComponent(
    modifier: Modifier = Modifier,
    timetableEntries: List<Timetable> = listOf(),
    onWeekDayClicked: (DayOfWeek) -> Unit = {}
) {
    var weekDaySelected by remember { mutableStateOf("Segunda") }

    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
        DayOfWeekHeader(onClick = {
            onWeekDayClicked(it)
            weekDaySelected = it.name
        })

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = weekDaySelected,
            textAlign = TextAlign.Center
        )

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(timetableEntries) {
                ScheduleCard(it)
            }
        }

        if(timetableEntries.isEmpty()){
            Text(text = "Vazio!")
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun WeaklySubjectsComponentPreview() {
    TimeTableComponent()
}