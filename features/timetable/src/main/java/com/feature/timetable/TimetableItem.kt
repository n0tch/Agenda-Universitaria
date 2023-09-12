package com.feature.timetable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.core.designsystem.extensions.toMinuteAndSecond
import com.example.model.Subject
import com.example.model.Timetable

@Composable
fun TimetableItem(
    timetable: Timetable = Timetable(),
    subject: Subject = Subject()
) {
    ConstraintLayout(Modifier.fillMaxWidth()) {
        val (leftContent, divider, rightContent, bottomDivider) = createRefs()
        val startGuideLine = createGuidelineFromStart(0.25f)

        Column(
            modifier = Modifier
                .constrainAs(leftContent) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(divider.start)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(timetable.startTime.toMinuteAndSecond())
            Text(timetable.endTime.toMinuteAndSecond())
        }
        Divider(
            modifier = Modifier
                .width(2.dp)
                .constrainAs(divider) {
                    start.linkTo(startGuideLine)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                }
        )
        Column(
            modifier = Modifier
                .constrainAs(rightContent) {
                    start.linkTo(divider.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .padding(8.dp)
        ) {
            Text(text = subject.name)
            Text(text = subject.place)
            Text(text = subject.teacher)
        }

        Divider(
            modifier = Modifier.constrainAs(bottomDivider) {
                bottom.linkTo(parent.bottom)
            }
        )
    }
}

@Preview
@Composable
fun TimetableItemPreview() {
    TimetableItem()
}