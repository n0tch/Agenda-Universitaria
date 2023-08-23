package com.core.database.di

import com.core.database.exam.ExamDao
import com.core.database.exam.ExamDaoImp
import com.core.database.subject.SubjectDao
import com.core.database.subject.SubjectDaoImp
import com.core.database.timetable.TimetableDao
import com.core.database.timetable.TimetableDaoImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DatabaseModule {

    @Binds
    abstract fun bindsSubjectDao(
        dao: SubjectDaoImp
    ): SubjectDao

    @Binds
    abstract fun bindsExamDao(
        dao: ExamDaoImp
    ): ExamDao

    @Binds
    abstract fun bindsTimetableDao(
        dao: TimetableDaoImp
    ): TimetableDao
}
