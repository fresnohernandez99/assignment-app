package com.fresnohernandez.assignmentapp.di

import com.fresnohernandez.assignmentapp.data.repository.MainRepositoryImpl
import com.fresnohernandez.assignmentapp.domain.repository.MainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMainRepository(repositoryImpl: MainRepositoryImpl): MainRepository
}