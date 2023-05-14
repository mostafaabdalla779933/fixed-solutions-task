package com.fixedsolutions.fixedsolutionstask.domain.repo

import com.fixedsolutions.fixedsolutionstask.MovieListResponse
import com.fixedsolutions.fixedsolutionstask.data.model.SearchResultResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface IRepository {

    suspend fun getComingSoon(fromCache:Boolean) : Flow<Response<MovieListResponse>>

    suspend fun getInTheaters(fromCache:Boolean) : Flow<Response<MovieListResponse>>

    suspend fun getTopRatedMovies(fromCache:Boolean) : Flow<Response<MovieListResponse>>

    suspend fun getBoxOffice(fromCache:Boolean): Flow<Response<MovieListResponse>>

    suspend fun searchExpression(expression:String): Flow<Response<SearchResultResponse>>

}