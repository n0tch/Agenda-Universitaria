package com.example.agendauniversitaria.network.di

import com.example.agendauniversitaria.network.user.UserDataSource
import com.example.agendauniversitaria.network.user.UserDataSourceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UserDataSourceModule {

    @Binds
    abstract fun bindsUserDataSource(userDataSourceImp: UserDataSourceImp): UserDataSource

}