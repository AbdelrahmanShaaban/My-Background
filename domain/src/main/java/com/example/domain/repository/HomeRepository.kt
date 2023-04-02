package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.model.Data
import com.example.domain.model.Wallpaper
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getPhotoFromHome(nextPage : Int) : Wallpaper
}