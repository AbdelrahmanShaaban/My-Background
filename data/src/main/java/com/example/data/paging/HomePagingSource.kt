package com.example.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.apiRemote.ApiService
import com.example.data.repositoryImp.HomeRepositoryImp
import com.example.data.utils.Constants
import com.example.domain.model.Data
import com.example.domain.usecase.GetPhotos
import javax.inject.Inject


class HomePagingSource @Inject constructor(private val getPhotosUseCase: GetPhotos) : PagingSource<Int, Data>() {
    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        val nextPage = params.key ?: Constants.FIRST_INDEX
        return try {
            val responseHome = getPhotosUseCase.homePhoto(nextPage)
            LoadResult.Page(
                data = responseHome.data,
                prevKey = if (nextPage == Constants.FIRST_INDEX) null else nextPage.minus(1),
                nextKey = responseHome.paggination?.next?.page
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}