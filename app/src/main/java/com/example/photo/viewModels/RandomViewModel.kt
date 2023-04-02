package com.example.photo.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.data.paging.RandomPagingSource
import com.example.domain.usecase.GetRandomPhotos
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RandomViewModel @Inject constructor(private val getRandomPhotos: GetRandomPhotos) : ViewModel() {
    val randomPage = Pager(config = PagingConfig(30),
    pagingSourceFactory = { RandomPagingSource(getRandomPhotos)
    }).flow.cachedIn(viewModelScope)
}