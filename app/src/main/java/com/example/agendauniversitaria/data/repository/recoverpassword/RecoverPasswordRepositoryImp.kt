package com.example.agendauniversitaria.data.repository.recoverpassword

import com.example.agendauniversitaria.common.Result
import com.example.agendauniversitaria.network.recoverpassword.FirebaseRecoverPasswordDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecoverPasswordRepositoryImp @Inject constructor(
    private val firebaseRecoverPasswordDataSource: FirebaseRecoverPasswordDataSource
): RecoverPasswordRepository {
    override fun recoverPassword(email: String): Flow<Result<Boolean>> =
        firebaseRecoverPasswordDataSource.resetPassword(email)
}
