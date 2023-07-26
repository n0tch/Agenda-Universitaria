package com.core.network.di

import com.core.network.login.LoginDataProvider
import com.core.network.login.LoginDataProviderImp
import com.core.network.profile.ProfileDataProvider
import com.core.network.profile.ProfileDataProviderImp
import com.core.network.register.RegisterDataProvider
import com.core.network.register.RegisterDataProviderImp
import com.core.network.user.UserDataProvider
import com.core.network.user.UserDataProviderImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NetworkModule {

    @Binds
    abstract fun bindsLoginDataProvider(
        loginDataProviderImp: LoginDataProviderImp
    ): LoginDataProvider

    @Binds
    abstract fun bindsRegisterDataProvider(
        registerDataProviderImp: RegisterDataProviderImp
    ): RegisterDataProvider

    @Binds
    abstract fun bindsProfileDataProvider(
        profileDataProviderImp: ProfileDataProviderImp
    ): ProfileDataProvider

    @Binds
    abstract fun bindsUserDataProvider(
        userDataProviderImp: UserDataProviderImp
    ): UserDataProvider
}
