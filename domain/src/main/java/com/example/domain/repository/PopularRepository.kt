package com.example.domain.repository

import com.example.domain.model.Wallpaper

interface PopularRepository {

    suspend fun getPhotoFromPopular(nextPage : Int) : Wallpaper
}