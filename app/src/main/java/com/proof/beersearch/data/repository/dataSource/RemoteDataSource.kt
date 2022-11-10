package com.proof.beersearch.data.repository.dataSource

import com.proof.beersearch.data.Utils.Resource
import com.proof.beersearch.data.model.ApiResponse
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getListBeer(page: Int, pagePer: Int): Response<List<ApiResponse>>
    suspend fun getIdBeer(id: Int): Response<List<ApiResponse>>
    suspend fun getBeerSearch(name: String, page: Int, pagePer: Int): Response<List<ApiResponse>>
}