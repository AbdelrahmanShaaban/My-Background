package com.example.photo.di

import com.example.domain.repository.HomeRepository
import com.example.domain.repository.PopularRepository
import com.example.domain.repository.RandomRepository
import com.example.domain.usecase.GetPhotos
import com.example.domain.usecase.GetPopularPhotos
import com.example.domain.usecase.GetRandomPhotos
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideUseCase(homeRepository: HomeRepository): GetPhotos{
        return GetPhotos(homeRepository)
    }

    @Provides
    fun providePopularUseCase(popularRepository:  PopularRepository): GetPopularPhotos{
        return GetPopularPhotos(popularRepository)
    }

    @Provides
    fun provideRandomUseCase(randomRepository: RandomRepository) : GetRandomPhotos{
        return GetRandomPhotos(randomRepository)
    }


}