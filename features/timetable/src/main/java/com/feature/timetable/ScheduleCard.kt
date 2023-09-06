package com.feature.timetable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.core.designsystem.extensions.toMinuteAndSecond
import com.example.model.Subject
import com.example.model.Timetable
import com.example.model.TimetableCompound
import java.time.DayOfWeek

@Composable
fun ScheduleCard(item: TimetableCompound, onNotificationClicked: @Composable (TimetableCompound) -> Unit = {}) {

    var toggle by remember { mutableStateOf(false) }

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            ConstraintLayout(Modifier.fillMaxWidth()) {
                val (startRef, endRef, subjectRef, teacherRef, placeRef, notificationRef) = createRefs()

                Text(
                    modifier = Modifier.constrainAs(startRef) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    },
                    text = item.timetable.startTime.toMinuteAndSecond()
                )

                Text(
                    modifier = Modifier.constrainAs(endRef) {
                        start.linkTo(parent.start)
                        top.linkTo(startRef.bottom)
                    },
                    text = item.timetable.endTime.toMinuteAndSecond()
                )

                Text(
                    modifier = Modifier.constrainAs(teacherRef) {
                        top.linkTo(endRef.bottom)
                        start.linkTo(parent.start)
                    },
                    text = item.subject.teacher
                )

                Text(
                    modifier = Modifier.constrainAs(subjectRef) {
                        top.linkTo(parent.top)
                        bottom.linkTo(placeRef.top)
                        start.linkTo(parent.start, margin = 4.dp)
                        end.linkTo(parent.end, margin = 4.dp)
                        width = Dimension.wrapContent
                        verticalChainWeight = 0f
                    },
                    text = item.subject.name,
                    textAlign = TextAlign.Center,
                )

                Text(
                    modifier = Modifier.constrainAs(placeRef) {
                        top.linkTo(subjectRef.bottom)
                        start.linkTo(subjectRef.start)
                        end.linkTo(subjectRef.end)
                        bottom.linkTo(parent.bottom)
                    },
                    text = item.subject.place
                )

                IconToggleButton(
                    modifier = Modifier.constrainAs(notificationRef){
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    },
                    checked = toggle,
                    onCheckedChange = {
                        toggle = it
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Notifications,
                        contentDescription = "Notification icon"
                    )
                }
            }
        }
    }

    if(toggle){
        onNotificationClicked(item)
    }
}

@Preview
@Composable
fun ScheduleCardPreview() {
    ScheduleCard(TimetableCompound())
}