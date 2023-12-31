package com.core.network.profile

import android.net.Uri
import com.core.network.model.toCurrentUserResponse
import com.core.network.model.userResponse.CurrentUserResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

internal class ProfileDataProviderImp @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseStorage: FirebaseStorage
): ProfileDataProvider {

    private suspend fun uploadImageProfile(imageUri: Uri?, userId: String): String {
        return imageUri?.let {
            val profileRef = firebaseStorage
                .reference
                .child("profileImages/$userId/${imageUri.lastPathSegment}")

            profileRef.putFile(it).await()
            profileRef.downloadUrl.await().toString()
        } ?: run { "" }
    }

    override suspend fun updateProfile(userName: String, photo: Uri?): CurrentUserResponse {
        val userUpdates = userProfileChangeRequest {
            displayName = userName
            photoUri = Uri.parse(uploadImageProfile(photo, firebaseAuth.currentUser?.uid ?: ""))
        }

        firebaseAuth.currentUser?.updateProfile(userUpdates)?.await()
        return firebaseAuth.currentUser.toCurrentUserResponse()
    }

    override fun resetPassword(email: String): Flow<Boolean> = flow {
        firebaseAuth.sendPasswordResetEmail(email).await()
        emit(true)
    }
}
