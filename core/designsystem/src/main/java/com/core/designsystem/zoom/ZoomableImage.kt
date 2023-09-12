package com.core.designsystem.zoom

import android.net.Uri
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.core.designsystem.components.alert.BasicAlertDialog

@Composable
fun ZoomableImage(uri: Uri, onDismiss: () -> Unit) {

    var scale by remember {
        mutableStateOf(1f)
    }
    var offset by remember { mutableStateOf(Offset.Zero) }

    val state = rememberTransformableState{ zoomChange, offsetChange, _ ->
        scale *= zoomChange
        offset += offsetChange
    }

    BasicAlertDialog(onDismiss = onDismiss) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (closeRef, imageRef) = createRefs()

            AsyncImage(
                model = uri,
                contentDescription = "",
                modifier = Modifier.constrainAs(imageRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                    width = Dimension.wrapContent
                    height = Dimension.wrapContent
                }.graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offset.x,
                    translationY = offset.y,
                ).transformable(state = state)
            )

            IconButton(
                onClick = onDismiss,
                modifier = Modifier.constrainAs(closeRef) {
                    top.linkTo(imageRef.top)
                    end.linkTo(imageRef.end)
                }
            ) {
                Icon(imageVector = Icons.Filled.Close, contentDescription = null)
            }
        }
    }
}