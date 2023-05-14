package com.fixedsolutions.fixedsolutionstask.di

import com.fixedsolutions.fixedsolutionstask.data.local.MovieDao
import com.fixedsolutions.fixedsolutionstask.data.remote.ApiService
import com.fixedsolutions.fixedsolutionstask.data.repo.Repository
import com.fixedsolutions.fixedsolutionstask.domain.repo.IRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {


    /**
     * Provides [Repository] instance
     */
    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService,movieDao: MovieDao,@IoDispatcher dispatcher: CoroutineDispatcher): IRepository {
        return Repository(apiService,movieDao,dispatcher)
    }
}