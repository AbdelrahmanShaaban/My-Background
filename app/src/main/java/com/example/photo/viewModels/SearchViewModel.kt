package com.example.photo.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.data.paging.SearchPagingSource
import com.example.data.repositoryImp.SearchCategoryRepository
import com.example.domain.model.Data
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel() {
    private val searchRepository = SearchCategoryRepository()
    var data: MutableLiveData<PagingData<Data>> = MutableLiveData()
    fun searchFromApi(keyWord: String) {
        viewModelScope.launch {
            Pager(config = PagingConfig(pageSize = 30),
                pagingSourceFactory = { SearchPagingSource(searchRepository.getPhotoFromSearch(), keyWord) }
            ).flow.cachedIn(viewModelScope).collect {
                data.postValue(it)
            }
        }
    }
}