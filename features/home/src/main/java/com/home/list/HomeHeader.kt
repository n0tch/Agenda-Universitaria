package com.home.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.disk.DiskCache
import coil.memory.MemoryCache

@Composable
fun HomeHeader(
    userName: String,
    photoUrl: String,
    onProfileClick: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (name, photo) = createRefs()

            AsyncImage(
                imageLoader = ImageLoader.Builder(context)
                    .memoryCache {
                        MemoryCache.Builder(context)
                            .maxSizePercent(0.25)
                            .build()
                    }
                    .diskCache {
                        DiskCache.Builder()
                            .directory(context.cacheDir.resolve("image_cache"))
                            .maxSizePercent(0.02)
                            .build()
                    }
                    .build(),
                modifier = Modifier
                    .constrainAs(photo) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    }
                    .size(48.dp)
                    .clip(CircleShape)
                    .clickable { onProfileClick() },
                model = photoUrl,
                contentDescription = "profile image",
                contentScale = ContentScale.Crop
            )

            Text(
                modifier = Modifier
                    .constrainAs(name) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    },
                text = "Suas Notas, $userName"
            )
        }
    }
}

@Preview
@Composable
fun HomeHeaderPreview() {
    HomeHeader("asas", "") {}
}