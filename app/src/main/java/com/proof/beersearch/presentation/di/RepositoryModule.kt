package com.proof.beersearch.presentation.di

import com.proof.beersearch.data.repository.RepositoryImpl
import com.proof.beersearch.data.repository.dataSource.RemoteDataSource
import com.proof.beersearch.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideRepository(
        remoteDataSource: RemoteDataSource
    ): Repository {
        return RepositoryImpl(remoteDataSource)
    }
}