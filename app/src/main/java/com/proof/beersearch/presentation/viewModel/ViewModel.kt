package com.proof.beersearch.presentation.viewModel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.proof.beersearch.data.Utils.Resource
import com.proof.beersearch.data.model.ApiResponse
import com.proof.beersearch.data.model.Beer
import com.proof.beersearch.domain.usecase.GetDetailUseCase
import com.proof.beersearch.domain.usecase.GetListUseCase
import com.proof.beersearch.domain.usecase.GetSearchUseCase
import com.proof.beersearch.view.pagin.ResultDataSource
import com.proof.beersearch.view.pagin.ResultSearchDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.lang.Exception

class ViewModel(
    private val app: Application,
    private val getListUseCase: GetListUseCase,
    private val getDetailUseCase: GetDetailUseCase,
    private val getSearchUseCase: GetSearchUseCase
) : AndroidViewModel(app) {

    val name = MutableLiveData<String>()

    private fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false

    }

    val resultListBeer: Flow<PagingData<Beer>> = Pager(PagingConfig(pageSize = 50)){
        ResultDataSource()
    }.flow.cachedIn(viewModelScope)

    private val _getBeerDetail: MutableLiveData<Resource<ApiResponse>> = MutableLiveData()
    val getBeerDetail get() = _getBeerDetail

    fun getBeerDetailResponse(id: Int) = viewModelScope.launch (Dispatchers.IO){
        getBeerDetail.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)) {
                val apiResult = getDetailUseCase.execute(id)
                getBeerDetail.postValue(apiResult)
            } else {
                getBeerDetail.postValue(Resource.Error("Internet is not available"))
            }
        } catch (e: Exception) {
            getBeerDetail.postValue(Resource.Error(e.message.toString()))
        }
    }


    val resultSearchBeer: Flow<PagingData<Beer>> = Pager(PagingConfig(pageSize = 50)){
        ResultSearchDataSource(name.value.toString())
    }.flow.cachedIn(viewModelScope)


}