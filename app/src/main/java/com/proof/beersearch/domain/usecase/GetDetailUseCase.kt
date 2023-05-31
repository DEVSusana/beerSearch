package com.proof.beersearch.domain.usecase

import com.proof.beersearch.data.Utils.Resource
import com.proof.beersearch.data.model.ApiResponse
import com.proof.beersearch.domain.repository.Repository

class GetDetailUseCase(private val repository: Repository) {
    suspend fun execute(id: Int): Resource<List<ApiResponse>>{
        return repository.getIdBeer(id)
    }
}