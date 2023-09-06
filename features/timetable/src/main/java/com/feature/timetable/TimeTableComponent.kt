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
import com.example.model.TimetableCompound
import com.feature.timetable.notification.CreateNotificationDialog
import java.time.DayOfWeek

@Composable
fun TimeTableComponent(
    modifier: Modifier = Modifier,
    timetableEntries: List<TimetableCompound> = listOf(),
    onWeekDayClicked: (DayOfWeek) -> Unit = {},
    saveNotification: (List<Long>) -> Unit = {}
) {
    var weekDaySelected by remember { mutableStateOf("Segunda") }

    val openNotification: @Composable (TimetableCompound) -> Unit = { timetable ->
        var isOpen by remember { mutableStateOf(true) }

        if(isOpen){
            CreateNotificationDialog(
                timetable = timetable,
                onDismiss = { isOpen = false },
                onSaveNotification = saveNotification
            )
        }
    }

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
                ScheduleCard(
                    item = it,
                    onNotificationClicked = {
                        openNotification(it)
                    }
                )
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