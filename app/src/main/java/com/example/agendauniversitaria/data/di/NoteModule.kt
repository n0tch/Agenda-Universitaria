package com.example.agendauniversitaria.data.di

import com.example.agendauniversitaria.data.repository.note.NoteRepository
import com.example.agendauniversitaria.data.repository.note.NoteRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NoteModule {

    @Binds
    abstract fun bindNoteRepository(repo: NoteRepositoryImp): NoteRepository

}