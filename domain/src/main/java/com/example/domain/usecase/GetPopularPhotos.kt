package com.example.domain.usecase

import com.example.domain.repository.PopularRepository

class GetPopularPhotos(private val popularRepository: PopularRepository){

suspend fun popularPhoto(nextPage : Int) = popularRepository.getPhotoFromPopular(nextPage)
}