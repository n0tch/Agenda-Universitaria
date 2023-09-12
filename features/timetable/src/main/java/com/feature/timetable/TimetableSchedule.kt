package com.feature.timetable

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.core.designsystem.components.schedule.EventScheduleItem
import com.example.model.TimetableCompound
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun TimetableSchedule(
    timetable: Map<DayOfWeek, List<TimetableCompound>>
) {
    LazyColumn {
        itemsIndexed(timetable.values.toList()) { index, item ->
            EventScheduleItem(
                title = DayOfWeek.of(index + 1).getDisplayName(TextStyle.SHORT, Locale.getDefault())
            ) {
                if(item.isEmpty()){
                    Text("Sem eventos")
                }else{
                    item.forEach {
                        TimetableItem(it.timetable, it.subject)
                    }
                }
            }
        }
    }
}
