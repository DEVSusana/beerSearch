package com.proof.beersearch.presentation.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.proof.beersearch.domain.usecase.GetDetailUseCase
import com.proof.beersearch.domain.usecase.GetListUseCase
import com.proof.beersearch.domain.usecase.GetSearchUseCase
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val app: Application,
    private val getListUseCase: GetListUseCase,
    private val getDetailUseCase: GetDetailUseCase,
    private val getSearchUseCase: GetSearchUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            Application::class.java,
            GetListUseCase::class.java,
            GetDetailUseCase::class.java,
            GetSearchUseCase::class.java
        ).newInstance(
            app,
            getListUseCase,
            getDetailUseCase,
            getSearchUseCase
        )
    }
}