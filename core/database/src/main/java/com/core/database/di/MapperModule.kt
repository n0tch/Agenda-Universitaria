package com.core.database.di

import com.core.database.mapper.ExamMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MapperModule {

    @Provides
    fun provideExamMapper() = ExamMapper()
}