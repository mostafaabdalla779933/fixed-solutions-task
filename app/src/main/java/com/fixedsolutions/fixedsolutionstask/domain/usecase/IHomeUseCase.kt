package com.fixedsolutions.fixedsolutionstask.domain.usecase

import com.fixedsolutions.fixedsolutionstask.MovieListResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface IHomeUseCase {

    suspend fun getComingSoon(): Flow<Response<MovieListResponse>>

    suspend fun getInTheaters(): Flow<Response<MovieListResponse>>
}