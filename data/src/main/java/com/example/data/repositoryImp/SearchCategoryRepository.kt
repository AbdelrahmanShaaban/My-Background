package com.example.data.repositoryImp

import com.example.data.apiRemote.ApiRetrofit
import com.example.data.apiRemote.ApiService

class SearchCategoryRepository {
    fun getPhotoFromSearch() : ApiService = ApiRetrofit.apiService
}