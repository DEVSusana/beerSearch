package com.proof.beersearch.data.repository.dataSourceImpl

import com.proof.beersearch.data.Utils.Resource
import com.proof.beersearch.data.api.ApiService
import com.proof.beersearch.data.model.ApiResponse
import com.proof.beersearch.data.repository.dataSource.RemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val apiService: ApiService) :
    RemoteDataSource {
    override suspend fun getListBeer(page: Int, pagePer: Int): Response<ApiResponse> {
        return apiService.getBeersList(page,pagePer)
    }

    override suspend fun getIdBeer(id: Int): Response<ApiResponse> {
        return apiService.getBeersById(id)
    }

    override suspend fun getBeerSearch(
        name: String,
        page: Int,
        pagePer: Int
    ): Response<ApiResponse> {
        return apiService.getSearchBeer(name, page, pagePer)
    }
}