package com.core.database.di

import com.core.database.realmModel.ExamRealm
import com.core.database.realmModel.SubjectNoteRealm
import com.core.database.realmModel.SubjectRealm
import com.core.database.realmModel.TimetableRealm
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

@Module
@InstallIn(SingletonComponent::class)
class RealmModule {

    @Provides
    fun providesRealm() = Realm.open(
        RealmConfiguration.create(
            schema = setOf(
                SubjectRealm::class,
                ExamRealm::class,
                SubjectNoteRealm::class,
                TimetableRealm::class
            )
        )
    )
}
