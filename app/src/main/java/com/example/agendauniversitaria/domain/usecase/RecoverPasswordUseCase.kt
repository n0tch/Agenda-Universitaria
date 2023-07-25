package com.example.agendauniversitaria.domain.usecase

import com.example.agendauniversitaria.common.AppDispatcher
import com.example.agendauniversitaria.common.Dispatcher
import com.example.agendauniversitaria.common.Result
import com.example.agendauniversitaria.data.repository.recoverpassword.RecoverPasswordRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RecoverPasswordUseCase @Inject constructor(
    @Dispatcher(AppDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
    private val recoverPasswordRepository: RecoverPasswordRepository
) {

      operator fun invoke(email: String): Flow<Result<Boolean>> =
          recoverPasswordRepository.recoverPassword(email).flowOn(ioDispatcher)

}