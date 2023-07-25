package com.example.agendauniversitaria.data.repository.login

import com.example.agendauniversitaria.common.Result
import com.example.agendauniversitaria.domain.model.LoginSocial
import com.example.agendauniversitaria.domain.model.LoginSocialEnum
import com.example.agendauniversitaria.domain.model.UserCredentials
import com.example.agendauniversitaria.domain.model.CurrentUser
import com.example.agendauniversitaria.network.login.FirebaseLoginDataSource
import com.example.agendauniversitaria.network.model.toCurrentUserModel
import com.example.agendauniversitaria.network.profile.ProfileDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepositoryImp @Inject constructor(
    private val firebaseNetwork: FirebaseLoginDataSource,
    private val profileDataSource: ProfileDataSource
) : LoginRepository {

    override fun loginWithEmailAndPassword(userCredentials: UserCredentials): Flow<Result<CurrentUser>> =
        flow {
            firebaseNetwork.loginWithCredentials(userCredentials).collect {
                when (it) {
                    is Result.Error -> emit(it)
                    is Result.Success -> {
//                        val userProfileImage = profileDataSource.fetchUserImageUrl(it.data?.uid ?: "")
                        emit(Result.Success(it.data.toCurrentUserModel()))
                    }
                }
            }
        }

    override fun loginWithSocial(loginSocial: LoginSocial): Flow<Result<CurrentUser>> = flow {
        when (loginSocial.type) {
            LoginSocialEnum.GOOGLE -> firebaseNetwork.loginWithGoogle()
            LoginSocialEnum.FACEBOOK -> firebaseNetwork.loginWithFacebook()
            LoginSocialEnum.ANONYMOUS -> firebaseNetwork.loginAnonymous()
        }.collect {
            when (it) {
                is Result.Error -> emit(it)
                is Result.Success -> emit(Result.Success(it.data.toCurrentUserModel()))
            }
        }
    }

    override fun logout(): Flow<Result<Boolean>> = firebaseNetwork.logout()
}
