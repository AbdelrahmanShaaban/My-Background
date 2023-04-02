package com.example.photo.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.data.paging.CategoryPagingSource
import com.example.data.repositoryImp.CategoryRepositoryImp
import com.example.domain.model.Data
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

//@HiltViewModel
class CategoryViewModel constructor(
    private val categoryName: String
) : ViewModel() {
    private val repository = CategoryRepositoryImp()
    private val _photoCategory: MutableLiveData<PagingData<Data>> = MutableLiveData()
    val categoryPhotos: LiveData<PagingData<Data>> = _photoCategory

    init {
        viewModelScope.launch {
            categoryPage(categoryName).collect {
                _photoCategory.postValue(it)
            }
        }

    }
    private fun categoryPage(categoryName: String): Flow<PagingData<Data>> {

        return Pager(config = PagingConfig(pageSize = 30),
            pagingSourceFactory = { CategoryPagingSource(repository.getPhotoFromCategory(), categoryName) }
        ).flow.cachedIn(viewModelScope)
    }

}