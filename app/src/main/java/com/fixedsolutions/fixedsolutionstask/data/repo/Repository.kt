package com.fixedsolutions.fixedsolutionstask.data.repo

import com.fixedsolutions.fixedsolutionstask.MovieListResponse
import com.fixedsolutions.fixedsolutionstask.data.remote.ApiService
import com.fixedsolutions.fixedsolutionstask.domain.repo.IRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val apiService : ApiService): IRepository {

    override suspend fun getComingSoon(): Flow<Response<MovieListResponse>> {
        return flow { emit(apiService.getComingSoon()) }
    }

    override suspend fun getInTheaters(): Flow<Response<MovieListResponse>> {
        return flow { emit(apiService.getInTheaters()) }
    }

    override suspend fun getTopRatedMovies(): Flow<Response<MovieListResponse>> {
        return flow { emit(apiService.getTopRatedMovies()) }
    }
}