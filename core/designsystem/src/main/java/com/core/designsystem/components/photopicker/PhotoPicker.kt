package com.core.designsystem.components.photopicker

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable

@Composable
fun photoPicker(
    onPhotoPicked: (Uri?) -> Unit
): ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?> {
    return rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            onPhotoPicked(it)
        }
    )
}

@Composable
fun multiplePhotoPicker(
    onPhotosPicked: (Uri?) -> Unit
): ManagedActivityResultLauncher<String, Uri?> {
    return rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
            onPhotosPicked(it)
        }
    )
}

val imageAndVideoContract = PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)