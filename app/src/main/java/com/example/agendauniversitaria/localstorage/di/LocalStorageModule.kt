package com.example.agendauniversitaria.localstorage.di

import com.example.agendauniversitaria.domain.model.CurrentUser
import com.example.agendauniversitaria.localstorage.StorageDataSource
import com.example.agendauniversitaria.localstorage.UserDataStorage
import com.example.agendauniversitaria.localstorage.UserStorageDataSourceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalStorageModule {

    @Binds
    abstract fun bindUserLocalStorage(userStorageDataSourceImp: UserStorageDataSourceImp): StorageDataSource<CurrentUser>

    @Binds
    abstract fun bindUserDataStorage(userStorageDataSourceImp: UserStorageDataSourceImp): UserDataStorage

}