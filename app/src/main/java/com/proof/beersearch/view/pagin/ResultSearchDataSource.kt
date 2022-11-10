package com.proof.beersearch.view.pagin

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.proof.beersearch.data.model.ApiResponse
import com.proof.beersearch.presentation.di.NetModule
import retrofit2.HttpException
import java.io.IOException

class ResultSearchDataSource(val name:String): PagingSource<Int, ApiResponse>() {

    override fun getRefreshKey(state: PagingState<Int, ApiResponse>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ApiResponse> {
        return try {
            val nextPage = params.key ?: 1
            val beerList = NetModule.provideApiService(NetModule.provideRetrofit()).getSearchBeer(name, nextPage, 50)

            LoadResult.Page(
                data = beerList.body()!!,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

}