package com.example.agendauniversitaria.data.di

import com.example.agendauniversitaria.data.repository.login.LoginRepository
import com.example.agendauniversitaria.data.repository.login.LoginRepositoryImp
import com.example.agendauniversitaria.data.repository.profile.ProfileRepository
import com.example.agendauniversitaria.data.repository.profile.ProfileRepositoryImp
import com.example.agendauniversitaria.data.repository.recoverpassword.RecoverPasswordRepository
import com.example.agendauniversitaria.data.repository.recoverpassword.RecoverPasswordRepositoryImp
import com.example.agendauniversitaria.data.repository.register.RegisterRepository
import com.example.agendauniversitaria.data.repository.register.RegisterRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LoginModule {

    @Binds
    abstract fun bindLoginRepository(
        repo: LoginRepositoryImp
    ): LoginRepository

    @Binds
    abstract fun bindRecoverPasswordRepository(
        repo: RecoverPasswordRepositoryImp
    ): RecoverPasswordRepository

    @Binds
    abstract fun bindRegisterRepository(
        repo: RegisterRepositoryImp
    ): RegisterRepository

    @Binds
    abstract fun bindProfileRepository(
        repo: ProfileRepositoryImp
    ): ProfileRepository
}