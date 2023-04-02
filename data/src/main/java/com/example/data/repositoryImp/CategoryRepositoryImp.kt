package com.example.data.repositoryImp

import com.example.data.apiRemote.ApiRetrofit
import com.example.data.apiRemote.ApiService

class CategoryRepositoryImp {
     fun getPhotoFromCategory(): ApiService = ApiRetrofit.apiService
}