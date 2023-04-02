package com.example.data.repositoryImp

import com.example.data.apiRemote.ApiService
import com.example.domain.model.Wallpaper
import com.example.domain.repository.RandomRepository

class RandomRepositoryImp(private val apiServer : ApiService) : RandomRepository {
    override suspend fun getPhotoFromRandom(nextPage: Int): Wallpaper = apiServer.getRandomFromApi(nextPage)
}