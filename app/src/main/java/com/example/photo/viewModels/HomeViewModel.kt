package com.example.photo.viewModels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.data.paging.HomePagingSource
import com.example.domain.usecase.GetPhotos

import dagger.hilt.android.lifecycle.HiltViewModel

import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotos
) : ViewModel() {

    val homePage = Pager(config = PagingConfig(30),
    pagingSourceFactory = {
        HomePagingSource(getPhotosUseCase)
    }).flow.cachedIn(viewModelScope)

 /*  private var _photos: MutableStateFlow<Wallpaper?> = MutableStateFlow(null)
   val photos: StateFlow<Wallpaper?> = _photos

    fun getPhotos(){
        viewModelScope.launch {
            try {
                _photos.value = getPhotosUseCase()
            }catch (e: Exception){
                Log.e("PhotosViewModel", e.message.toString() )
            }

        }

    }*/

}