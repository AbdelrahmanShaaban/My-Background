package com.example.domain.repository

import com.example.domain.model.Wallpaper

interface RandomRepository {

    suspend fun getPhotoFromRandom(nextPage : Int) : Wallpaper
}