package com.fixedsolutions.fixedsolutionstask.data.remote

import com.fixedsolutions.fixedsolutionstask.MovieListResponse
import com.fixedsolutions.fixedsolutionstask.common.API_KEY
import com.fixedsolutions.fixedsolutionstask.data.model.SearchResultResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET("en/API/ComingSoon/$API_KEY")
    suspend fun getComingSoon(): Response<MovieListResponse>


    @GET("en/API/InTheaters/$API_KEY")
    suspend fun getInTheaters(): Response<MovieListResponse>


    @GET("en/API/Top250Movies/$API_KEY")
    suspend fun getTopRatedMovies(): Response<MovieListResponse>


    @GET("en/API/BoxOffice/$API_KEY")
    suspend fun getBoxOffice(): Response<MovieListResponse>


    @GET
    suspend fun searchExpression(@Url api: String): Response<SearchResultResponse>
}