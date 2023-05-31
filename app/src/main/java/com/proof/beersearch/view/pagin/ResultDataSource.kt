package com.proof.beersearch.view.pagin

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.proof.beersearch.data.model.ApiResponse
import com.proof.beersearch.presentation.di.NetModule
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException

class ResultDataSource(val name: String) : PagingSource<Int, ApiResponse>() {

    override fun getRefreshKey(state: PagingState<Int, ApiResponse>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ApiResponse> {
        try {
            if (name.isNotEmpty()) {
                val outName = name.replace(" ", "_")
                val nextPage = 1
                val beerList = NetModule.provideApiService(NetModule.provideRetrofit())
                    .getSearchBeer(outName, nextPage, 50)

                return LoadResult.Page(
                    data = if(!beerList.body().isNullOrEmpty()) beerList.body()!! else emptyList(),
                    prevKey = null,
                    nextKey = null
                )

            } else {
                val nextPage = params.key ?: 1
                val beerList = NetModule.provideApiService(NetModule.provideRetrofit())
                    .getBeersList(nextPage, 50)

                return LoadResult.Page(
                    data = beerList.body()!!,
                    prevKey = if (nextPage == 1) null else nextPage - 1,
                    nextKey = nextPage + 1
                )
            }
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

}