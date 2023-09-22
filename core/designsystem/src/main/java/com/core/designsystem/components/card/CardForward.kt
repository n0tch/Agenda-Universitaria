package com.core.designsystem.components.card

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardForward(
    modifier: Modifier = Modifier,
    title: String,
    body: String,
    indicatorIcon: ImageVector = Icons.Filled.ChevronRight,
    onClick: () -> Unit
) {
    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 2.dp, vertical = 2.dp)
            .height(74.dp),
        onClick = { onClick() }
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize().padding(6.dp)) {
            val (textRef, bodyRef, indicatorRef) = createRefs()

            Text(
                modifier = Modifier.constrainAs(textRef) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                },
                text = title,
                fontStyle = MaterialTheme.typography.labelMedium.fontStyle
            )
            Text(
                modifier = Modifier.constrainAs(bodyRef) {
                    top.linkTo(textRef.bottom)
                    start.linkTo(parent.start)
                },
                text = body,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Icon(
                modifier = Modifier.constrainAs(indicatorRef) {
                    end.linkTo(parent.end)
                    top.linkTo(textRef.top)
                    bottom.linkTo(bodyRef.bottom)
                },
                imageVector = indicatorIcon,
                contentDescription = ""
            )

        }
    }
}

@Preview
@Composable
fun CardForwardPreview() {
    CardForward(
        title = "Titulo",
        body = "A descrição do card vai aparecer nessa linha. Caso seja pequeno, deverá cortar linha"
    ) {

    }
}