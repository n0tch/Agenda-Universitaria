package com.home.home.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
            .fillMaxHeight(0.5f)
            .fillMaxHeight()
            .background(Color.Black)
    ) {
        val (photoRef, screenList) = createRefs()

        AsyncImage(
            modifier = Modifier.constrainAs(photoRef) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
            },
            model = photoUrl,
            contentDescription = "Profile Image"
        )

        LazyColumn(
            modifier = Modifier.constrainAs(screenList) {
                top.linkTo(photoRef.bottom)
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