package com.fixedsolutions.fixedsolutionstask.domain.repo

import com.fixedsolutions.fixedsolutionstask.MovieListResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface IRepository {

    suspend fun getComingSoon() : Flow<Response<MovieListResponse>>

    suspend fun getInTheaters() : Flow<Response<MovieListResponse>>

}