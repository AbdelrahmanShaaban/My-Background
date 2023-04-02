package com.example.domain.usecase

import com.example.domain.repository.HomeRepository

class GetPhotos(private val homeRepository: HomeRepository) {
    suspend fun homePhoto(nextPage : Int) = homeRepository.getPhotoFromHome(nextPage)
}