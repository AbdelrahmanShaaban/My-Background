package com.example.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.apiRemote.ApiService
import com.example.data.repositoryImp.CategoryRepositoryImp
import com.example.data.utils.Constants
import com.example.domain.model.Data

class CategoryPagingSource (
    private val apiService: ApiService,
    private val categoryName : String
) : PagingSource<Int , Data>() {
    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {

        return try {

            val nextPage = params.key ?: Constants.FIRST_INDEX
            val response = apiService.getCategoryFromApi(nextPage , categoryName)
            LoadResult.Page(
                data = response.data,
                prevKey = if (nextPage == Constants.FIRST_INDEX) null else nextPage.minus(1),
                nextKey = response.paggination?.next?.page
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }

}