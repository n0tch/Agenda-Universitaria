package com.core.data.di

import com.core.data.repository.login.LoginRepository
import com.core.data.repository.login.LoginRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataRepositoryModule {

    @Binds
    abstract fun bindsLoginRepository(repository: LoginRepositoryImp): LoginRepository

}