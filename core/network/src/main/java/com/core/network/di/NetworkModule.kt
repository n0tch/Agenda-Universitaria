package com.core.network.di

import com.core.network.login.LoginDataProvider
import com.core.network.login.LoginDataProviderImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NetworkModule {

    @Binds
    abstract fun bindsLoginDataProvider(loginDataProviderImp: LoginDataProviderImp): LoginDataProvider
}
