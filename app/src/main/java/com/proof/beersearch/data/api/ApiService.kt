package com.proof.beersearch.data.api

import com.proof.beersearch.data.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("beers")
    fun getBeersById(
        @Query("ids") id: Int
    ): Response<ApiResponse>

    @GET("beers")
    fun getBeersList(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<ApiResponse>

    @GET("beers")
    fun getSearchBeer(
        @Query("beer_name") beerName: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<ApiResponse>

}