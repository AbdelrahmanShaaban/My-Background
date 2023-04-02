package com.example.domain.usecase

import com.example.domain.repository.RandomRepository

class GetRandomPhotos(private val randomRepository: RandomRepository) {
    suspend fun randomPhotos(nextPage : Int)  = randomRepository.getPhotoFromRandom(nextPage)
}