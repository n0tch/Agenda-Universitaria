package com.core.data.di

import com.core.data.repository.login.LoginRepository
import com.core.data.repository.login.LoginRepositoryImp
import com.core.data.repository.note.NoteRepository
import com.core.data.repository.note.NoteRepositoryImp
import com.core.data.repository.profile.ProfileRepository
import com.core.data.repository.profile.ProfileRepositoryImp
import com.core.data.repository.register.RegisterRepository
import com.core.data.repository.register.RegisterRepositoryImp
import com.core.data.repository.subject.SubjectRepository
import com.core.data.repository.subject.SubjectRepositoryImp
import com.core.data.repository.timetable.TimetableRepository
import com.core.data.repository.timetable.TimetableRepositoryImp
import com.core.data.repository.user.UserRepository
import com.core.data.repository.user.UserRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataRepositoryModule {

    @Binds
    abstract fun bindsLoginRepository(repository: LoginRepositoryImp): LoginRepository

    @Binds
    abstract fun bindsRegisterRepository(repository: RegisterRepositoryImp): RegisterRepository

    @Binds
    abstract fun bindsProfileRepository(repository: ProfileRepositoryImp): ProfileRepository

    @Binds
    abstract fun bindsUserRepository(repository: UserRepositoryImp): UserRepository

    @Binds
    abstract fun bindsNoteRepository(repository: NoteRepositoryImp): NoteRepository

    @Binds
    abstract fun bindsSubjectRepository(repository: SubjectRepositoryImp): SubjectRepository

    @Binds
    abstract fun bindTimetableRepository(repository: TimetableRepositoryImp): TimetableRepository
}