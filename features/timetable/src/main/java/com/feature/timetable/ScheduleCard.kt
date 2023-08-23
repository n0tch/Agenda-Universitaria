package com.feature.timetable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.model.TimetableEntry

@Composable
fun ScheduleCard(item: TimetableEntry) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            ConstraintLayout(Modifier.fillMaxWidth()) {
                val (startRef, endRef, subjectRef, teacherRef, placeRef, dividerRef) = createRefs()

                Text(
                    modifier = Modifier.constrainAs(startRef) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    },
                    text = item.startTime
                )

                Text(
                    modifier = Modifier.constrainAs(endRef) {
                        start.linkTo(parent.start)
                        top.linkTo(startRef.bottom)
                    },
                    text = item.endTime
                )

                Text(
                    modifier = Modifier.constrainAs(teacherRef) {
                        top.linkTo(endRef.bottom)
                        start.linkTo(parent.start)
                    },
                    text = "item.teacherName"
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
                    text = "item.subject",
                    textAlign = TextAlign.Center,
                )

                Text(
                    modifier = Modifier.constrainAs(placeRef) {
                        top.linkTo(subjectRef.bottom)
                        start.linkTo(subjectRef.start)
                        end.linkTo(subjectRef.end)
                        bottom.linkTo(parent.bottom)
                    },
                    text = "item.placeName"
                )
            }
        }
    }
}

@Preview
@Composable
fun ScheduleCardPreview() {
    ScheduleCard(TimetableEntry(id = "",listOf("Segunda", "Quarta"), "19:00", "20:40", "ATP"))
}