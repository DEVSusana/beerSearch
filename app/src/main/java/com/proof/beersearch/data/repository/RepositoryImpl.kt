package com.proof.beersearch.data.repository

import com.proof.beersearch.data.Utils.Resource
import com.proof.beersearch.data.model.ApiResponse
import com.proof.beersearch.data.repository.dataSource.RemoteDataSource
import com.proof.beersearch.domain.repository.Repository
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : Repository {
    override suspend fun getListBeer(page: Int, pagePer: Int): Resource<ApiResponse> {
        return responseToResource(remoteDataSource.getListBeer(page, pagePer))
    }

    override suspend fun getIdBeer(id: Int): Resource<ApiResponse> {
        return responseToResource(remoteDataSource.getIdBeer(id))
    }

    override suspend fun getBeerSearch(
        name: String,
        page: Int,
        pagePer: Int
    ): Resource<ApiResponse> {
        return responseToResource(remoteDataSource.getBeerSearch(name, page, pagePer))
    }

    private fun responseToResource(response: Response<ApiResponse>): Resource<ApiResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }


}