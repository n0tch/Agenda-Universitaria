package com.example.agendauniversitaria.network.profile

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProfileDataSourceImp @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseStorage: FirebaseStorage,
) : ProfileDataSource {

    override suspend fun uploadImageProfile(imageUri: Uri?, userId: String): String = try {
        imageUri?.let {
            val profileRef = firebaseStorage
                .reference
                .child("profileImages/$userId/${imageUri.lastPathSegment}")

            profileRef.putFile(it).await()
            profileRef.downloadUrl.await().toString()
        } ?: run {
            throw Exception("none")
        }
    } catch (exception: Exception) {
        throw exception
    }

    override suspend fun fetchUserImageUrl(userId: String): String {
        return firebaseStorage
            .reference
            .child("profileImages/$userId")
            .list(1)
            .await()
            .items
            .first()
            .downloadUrl
            .await()
            .toString()
    }

    override suspend fun updateProfile(userName: String, photo: Uri?): FirebaseUser? {
        val userUpdates = userProfileChangeRequest {
            displayName = userName
            photoUri = Uri.parse(uploadImageProfile(photo, firebaseAuth.currentUser?.uid ?: ""))
        }

        firebaseAuth.currentUser?.updateProfile(userUpdates)?.await()
        return firebaseAuth.currentUser
    }
}
