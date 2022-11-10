package com.proof.beersearch.view.pagin

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.proof.beersearch.data.model.ApiResponse
import com.proof.beersearch.data.model.Beer
import com.proof.beersearch.presentation.di.NetModule
import retrofit2.HttpException
import java.io.IOException

class ResultDataSource: PagingSource<Int, Beer>() {

    override fun getRefreshKey(state: PagingState<Int, Beer>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Beer> {
        return try {
            val nextPage = params.key ?: 1
            val beerList = NetModule.provideApiService(NetModule.provideRetrofit()).getBeersList(nextPage, 50)

            LoadResult.Page(
                data = beerList.body()!!.beerList,
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