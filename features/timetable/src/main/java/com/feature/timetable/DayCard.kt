package com.feature.timetable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DayCard(
    modifier: Modifier = Modifier,
    isSelected: Boolean, text: String,
    onClick: (String) -> Unit
) {
    var isSelected by remember { mutableStateOf(isSelected) }

    val textStyleBody = MaterialTheme.typography.bodySmall
    var textStyle by remember { mutableStateOf(textStyleBody) }
    var readyToDraw by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .width(46.dp)
            .height(56.dp),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.tertiaryContainer
        ),
        onClick = {
            isSelected = isSelected.not()
            onClick(text)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
                .drawWithContent { if (readyToDraw) drawContent() },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = text.substring(0..2).replaceFirstChar { it.uppercase() },
                overflow = TextOverflow.Clip,
                style = textStyle,
                onTextLayout = {
                    if (it.didOverflowWidth) {
                        textStyle = textStyle.copy(fontSize = textStyle.fontSize * 0.5)
                    } else {
                        readyToDraw = true
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun DayCardSelectedPreview() {
    DayCard(isSelected = true, text = "segunda-feira", onClick = {})
}
