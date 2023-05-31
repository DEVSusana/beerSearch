package com.proof.beersearch.domain.repository

import com.proof.beersearch.data.Utils.Resource
import com.proof.beersearch.data.model.ApiResponse

interface Repository {
    suspend fun getListBeer(page: Int, pagePer: Int): Resource<List<ApiResponse>>
    suspend fun getIdBeer(id: Int): Resource<List<ApiResponse>>
    suspend fun getBeerSearch(name: String, page: Int, pagePer: Int): Resource<List<ApiResponse>>
}