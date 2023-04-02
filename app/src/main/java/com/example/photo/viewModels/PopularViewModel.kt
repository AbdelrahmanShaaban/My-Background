package com.example.photo.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.data.apiRemote.ApiService
import com.example.data.paging.PopularPagingSource
import com.example.domain.repository.HomeRepository
import com.example.domain.usecase.GetPhotos
import com.example.domain.usecase.GetPopularPhotos
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(private val getPhotosUseCase: GetPopularPhotos) :ViewModel() {
    val popularPage = Pager(config = PagingConfig(30) ,
    pagingSourceFactory = {
        PopularPagingSource(getPhotosUseCase)
    }).flow.cachedIn(viewModelScope)
}