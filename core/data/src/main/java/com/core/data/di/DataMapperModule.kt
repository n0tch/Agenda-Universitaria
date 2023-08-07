package com.core.data.di

import com.core.data.mapper.ExamMapper
import com.core.data.mapper.NoteMapper
import com.core.data.mapper.SubjectMapper
import com.core.data.mapper.TimetableMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataMapperModule {

    @Provides
    fun examMapper() = ExamMapper()

    @Provides
    fun noteMapper() = NoteMapper()

    @Provides
    fun timetableMapper() = TimetableMapper()

    @Provides
    fun subjectMapper() = SubjectMapper()
}
