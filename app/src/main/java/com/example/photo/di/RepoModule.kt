package com.example.photo.di

import com.example.data.apiRemote.ApiService
import com.example.data.repositoryImp.CategoryRepositoryImp
import com.example.data.repositoryImp.HomeRepositoryImp
import com.example.data.repositoryImp.PopularRepositoryImp
import com.example.data.repositoryImp.RandomRepositoryImp
import com.example.domain.repository.HomeRepository
import com.example.domain.repository.PopularRepository
import com.example.domain.repository.RandomRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun provideRepository(apiService: ApiService) :HomeRepository{
        return HomeRepositoryImp(apiService)
    }

    @Provides
    fun providePopularRepository(apiService: ApiService) : PopularRepository{
        return PopularRepositoryImp(apiService)
    }

    @Provides
    fun provideRandomRepository(apiService: ApiService) : RandomRepository {
        return RandomRepositoryImp(apiService)
    }

}