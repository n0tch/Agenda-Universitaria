package com.example.agendauniversitaria.data.repository.register

import android.net.Uri
import com.example.agendauniversitaria.data.repository.register.RegisterRepository
import com.example.agendauniversitaria.network.profile.ProfileDataSource
import com.example.agendauniversitaria.network.register.FirebaseRegisterDataSource
import com.example.agendauniversitaria.network.user.UserDataSource
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class RegisterRepositoryImp @Inject constructor(
    private val firebaseRegisterDataSource: FirebaseRegisterDataSource,
    private val profileDataSource: ProfileDataSource,
    private val userDataStorage: UserDataSource
) : RegisterRepository {

    override suspend fun signupWithUserCredentials(
        email: String,
        password: String,
        photoUri: Uri?
    ): FirebaseUser? = try {
        firebaseRegisterDataSource.signUnWithCredentials(email, password)
    } catch (exception: Exception) {
        throw exception
    }
}