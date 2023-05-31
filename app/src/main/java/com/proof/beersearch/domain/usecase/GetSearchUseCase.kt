package com.proof.beersearch.domain.usecase

import com.proof.beersearch.data.Utils.Resource
import com.proof.beersearch.data.model.ApiResponse
import com.proof.beersearch.domain.repository.Repository

class GetSearchUseCase(private val repository: Repository) {
    suspend fun execute(name: String, page: Int, pagePer: Int): Resource<List<ApiResponse>>{
        return repository.getBeerSearch(name, page, pagePer)
    }
}