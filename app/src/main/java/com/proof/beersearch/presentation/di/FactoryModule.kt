package com.proof.beersearch.presentation.di

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.proof.beersearch.domain.usecase.GetDetailUseCase
import com.proof.beersearch.domain.usecase.GetListUseCase
import com.proof.beersearch.domain.usecase.GetSearchUseCase
import com.proof.beersearch.presentation.viewModel.ViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FactoryModule {
    @Provides
    @Singleton
    fun provideViewModelFactory(
        application: Application,
        getListUseCase: GetListUseCase,
        getDetailUseCase: GetDetailUseCase,
        getSearchUseCase: GetSearchUseCase

    ): ViewModelProvider.Factory {
        return ViewModelFactory(
            application,
            getListUseCase,
            getDetailUseCase,
            getSearchUseCase
        )
    }
}