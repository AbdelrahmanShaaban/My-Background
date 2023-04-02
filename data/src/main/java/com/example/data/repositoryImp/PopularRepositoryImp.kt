package com.example.data.repositoryImp

import com.example.data.apiRemote.ApiService
import com.example.domain.model.Wallpaper
import com.example.domain.repository.PopularRepository
import javax.inject.Inject

class PopularRepositoryImp @Inject constructor(private val apiService: ApiService) : PopularRepository {
    override suspend fun getPhotoFromPopular(nextPage: Int): Wallpaper  = apiService.getPopularFromApi(nextPage)
}