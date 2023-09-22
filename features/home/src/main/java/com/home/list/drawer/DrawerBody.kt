package com.home.list.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage

@Composable
fun DrawerBody(
    photoUrl: String,
    screensList: List<String>,
    onDrawerItemClicked: (String) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        val (photoRef, screenList) = createRefs()

        Box(
            modifier = Modifier
                .border(1.dp, Color.Black, CircleShape)
                .padding(4.dp)
                .size(128.dp)
                .constrainAs(photoRef) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                imageVector = Icons.Filled.Person,
                contentDescription = "",
                modifier = Modifier
                    .padding(4.dp)
                    .size(48.dp)
            )
        }

        LazyColumn(
            modifier = Modifier.constrainAs(screenList) {
                top.linkTo(photoRef.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        ) {
            items(screensList) {
                Column(modifier = Modifier.clickable { onDrawerItemClicked(it) }) {
                    Text(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxSize(),
                        text = it,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Divider()
                }
            }
        }
    }
}

@Preview
@Composable
fun DrawerBodyPreview() {
    DrawerBody(photoUrl = "", screensList = listOf("Abc", "abc", "nas")) {}
}