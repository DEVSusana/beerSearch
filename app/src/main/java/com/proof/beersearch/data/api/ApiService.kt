package com.proof.beersearch.data.api

import com.proof.beersearch.data.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("beers")
    suspend fun getBeersById(
        @Query("ids") id: Int
    ): Response<List<ApiResponse>>

    @GET("beers")
    suspend fun getBeersList(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<List<ApiResponse>>

    @GET("beers")
    suspend fun getSearchBeer(
        @Query("beer_name") beerName: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<List<ApiResponse>>

}