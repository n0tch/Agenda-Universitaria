package com.core.database.di

import android.content.Context
import androidx.room.Room
import com.core.database.exam.ExamDao
import com.core.database.note.NoteDao
import com.core.database.label.LabelDao
import com.core.database.media.NoteMediaDao
import com.core.database.note.relations.NoteLabelDao
import com.core.database.subject.SubjectDao
import com.core.database.timetable.TimetableDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideRoomModule(
        @ApplicationContext context: Context
    ):AppDatabase = Room
        .databaseBuilder(
            context,
            AppDatabase::class.java,
            APP_DATABASE_NAME
        )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun providesNoteDao(room: AppDatabase): NoteDao = room.noteDao()

    @Provides
    fun providesLabelDao(room: AppDatabase): LabelDao = room.labelDao()

    @Provides
    fun providesSubjectDao(room: AppDatabase): SubjectDao = room.subjectDao()

    @Provides
    fun providesTimetableDao(room: AppDatabase): TimetableDao = room.timetableDao()

    @Provides
    fun providesExamDao(room: AppDatabase): ExamDao = room.examDao()

    @Provides
    fun providesNoteMediaDao(room: AppDatabase): NoteMediaDao = room.noteMediaDao()

    @Provides
    fun providesNoteLabelDao(room: AppDatabase): NoteLabelDao = room.noteLabelDao()

    companion object{
        const val APP_DATABASE_NAME = "agenda_universitaria"
    }
}
