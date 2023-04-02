package com.example.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.apiRemote.ApiService
import com.example.data.utils.Constants
import com.example.domain.model.Data

class SearchPagingSource(
    private val apiService: ApiService,
    private val categorySearchName: String
) :
    PagingSource<Int, Data>() {
    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {

        return try {
            val nextPage = params.key?:Constants.FIRST_INDEX
            val response = apiService.searchFromApi(nextPage , categorySearchName)
            LoadResult.Page(
                data = response.data ,
                prevKey = null ,
                nextKey = response.paggination?.next?.page
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }

    }
}