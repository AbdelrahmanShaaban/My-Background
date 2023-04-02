package com.example.data.repositoryImp
import com.example.data.apiRemote.ApiService
import com.example.data.utils.Constants
import com.example.domain.model.Wallpaper
import com.example.domain.repository.HomeRepository
import javax.inject.Inject
import javax.inject.Singleton


class HomeRepositoryImp @Inject constructor(private val apiService : ApiService) : HomeRepository {
    override suspend fun getPhotoFromHome(nextPage : Int): Wallpaper = apiService.getHomeFromApi(nextPage)
}

//Wallpaper = apiService.getHomeFromApi(Constants.FIRST_INDEX)