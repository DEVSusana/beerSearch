package com.proof.beersearch.presentation.di

import com.proof.beersearch.domain.repository.Repository
import com.proof.beersearch.domain.usecase.GetDetailUseCase
import com.proof.beersearch.domain.usecase.GetListUseCase
import com.proof.beersearch.domain.usecase.GetSearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideGetListUseCase(
        repository: Repository
    ): GetListUseCase {
        return GetListUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetDetailUseCase(
        repository: Repository
    ): GetDetailUseCase {
        return GetDetailUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetSearchUseCase(
        repository: Repository
    ): GetSearchUseCase {
        return GetSearchUseCase(repository)
    }

}