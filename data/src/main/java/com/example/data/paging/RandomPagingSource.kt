package com.example.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.utils.Constants
import com.example.domain.model.Data
import com.example.domain.usecase.GetRandomPhotos
import javax.inject.Inject

class RandomPagingSource @Inject constructor(private val getRandomPhotos: GetRandomPhotos) : PagingSource<Int , Data>() {
    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        val nextPage = params.key ?: Constants.FIRST_INDEX
        return try {
            val response = getRandomPhotos.randomPhotos(nextPage)
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