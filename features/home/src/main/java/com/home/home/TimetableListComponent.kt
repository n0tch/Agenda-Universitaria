package com.home.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.core.designsystem.extensions.localized
import com.core.designsystem.extensions.toMinuteAndSecond
import com.example.model.Timetable
import com.example.model.TimetableCompound
import java.time.DayOfWeek

@Composable
fun TimetableListComponent(
    dayOfWeek: DayOfWeek = DayOfWeek.MONDAY,
    timetableEntries: List<TimetableCompound> = listOf()
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
    ) {
        Column(Modifier.padding(12.dp)) {
            Text(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .fillMaxWidth(),
                text = dayOfWeek.localized(),
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )

            LazyColumn {
                items(timetableEntries) {
                    Text(text = it.timetable.startTime.toMinuteAndSecond())
                    Text(text = it.timetable.endTime.toMinuteAndSecond())
                    Text(text = it.subject.name)
                    Spacer(Modifier.height(4.dp))
                    Divider()
                }
            }

            if(timetableEntries.isEmpty())
                Text("Sem eventos para ${dayOfWeek.localized()}")
        }
    }
}

@Preview
@Composable
fun TimetableListComponentPreview() {
    TimetableListComponent()
}