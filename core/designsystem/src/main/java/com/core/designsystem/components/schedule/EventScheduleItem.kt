package com.core.designsystem.components.schedule

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Composable
fun EventScheduleItem(
    title: String = "",
    content: @Composable () -> Unit = {}
) {
    ConstraintLayout(Modifier.fillMaxWidth()) {
        val (
            weekDayRef,
            circleRef,
            dividerRef,
            cardRef,
        ) = createRefs()

        val startGuideLine = createGuidelineFromStart(0.16F)

        Text(
            text = title,
            modifier = Modifier
                .padding(0.dp)
                .constrainAs(weekDayRef) {
                    top.linkTo(parent.top)
                    end.linkTo(circleRef.start)
                    start.linkTo(parent.start)
                }
        )

        Canvas(
            modifier = Modifier
                .size(18.dp)
                .border(
                    width = 0.dp,
                    color = MaterialTheme.colorScheme.secondary,
                    shape = CircleShape
                )
                .constrainAs(circleRef) {
                    start.linkTo(startGuideLine)
                    top.linkTo(weekDayRef.top)
                    bottom.linkTo(weekDayRef.bottom)
                },
            onDraw = {
                drawCircle(Color.White)
            }
        )

        Divider(
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .width(4.dp)
                .constrainAs(dividerRef) {
                    top.linkTo(circleRef.bottom)
                    start.linkTo(circleRef.start)
                    end.linkTo(circleRef.end)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                }
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .constrainAs(cardRef) {
                    start.linkTo(circleRef.end)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                }
        ) {
            OutlinedCard(modifier = Modifier.fillMaxWidth()) {
                Column {
                    content()
                }
            }
        }
    }
}

@Preview
@Composable
fun TimetableScheduleItemPreview() {
    EventScheduleItem("Seg") {
        Text("asas")
        Text("asas")
        Text("asas")
    }
}