package com.core.network.di

import com.core.network.login.LoginDataProvider
import com.core.network.login.LoginDataProviderImp
import com.core.network.note.NoteDataProvider
import com.core.network.note.NoteDataProviderImp
import com.core.network.profile.ProfileDataProvider
import com.core.network.profile.ProfileDataProviderImp
import com.core.network.register.RegisterDataProvider
import com.core.network.register.RegisterDataProviderImp
import com.core.network.subject.SubjectDataProvider
import com.core.network.subject.SubjectDataProviderImp
import com.core.network.timetable.TimetableDataProvider
import com.core.network.timetable.TimetableDataProviderImp
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

    @Binds
    abstract fun bindsNoteDataProvider(
        noteDataProviderImp: NoteDataProviderImp
    ): NoteDataProvider

    @Binds
    abstract fun bindsSubjectDataProvider(
        subjectDataProviderImp: SubjectDataProviderImp
    ): SubjectDataProvider

    @Binds
    abstract fun bindTimetableDataProvider(
        timetableDataProviderImp: TimetableDataProviderImp
    ): TimetableDataProvider
}
