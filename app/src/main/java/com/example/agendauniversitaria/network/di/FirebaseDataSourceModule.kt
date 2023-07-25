package com.example.agendauniversitaria.network.di

import com.example.agendauniversitaria.network.login.FirebaseLoginDataSource
import com.example.agendauniversitaria.network.login.FirebaseLoginNetwork
import com.example.agendauniversitaria.network.note.NoteDataSource
import com.example.agendauniversitaria.network.note.NoteDataSourceImp
import com.example.agendauniversitaria.network.profile.ProfileDataSource
import com.example.agendauniversitaria.network.profile.ProfileDataSourceImp
import com.example.agendauniversitaria.network.recoverpassword.FirebaseRecoverPasswordDataSource
import com.example.agendauniversitaria.network.recoverpassword.FirebaseRecoverPasswordNetwork
import com.example.agendauniversitaria.network.register.FirebaseRegisterDataSource
import com.example.agendauniversitaria.network.register.FirebaseRegisterNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FirebaseDataSourceModule {

    @Binds
    abstract fun bindFirebaseDatasource(firebaseNetwork: FirebaseLoginNetwork) : FirebaseLoginDataSource

    @Binds
    abstract fun bindFirebaseRecoverDataSource(firebaseRecoverPasswordNetwork: FirebaseRecoverPasswordNetwork): FirebaseRecoverPasswordDataSource

    @Binds
    abstract fun bindFirebaseRegisterDataSource(firebaseRegisterNetwork: FirebaseRegisterNetwork): FirebaseRegisterDataSource

    @Binds
    abstract fun bindFirebaseProfileDataSource(profileDataSourceImp: ProfileDataSourceImp): ProfileDataSource

    @Binds
    abstract fun bindFirebaseNoteDataSource(noteDatasourceImp: NoteDataSourceImp): NoteDataSource
}