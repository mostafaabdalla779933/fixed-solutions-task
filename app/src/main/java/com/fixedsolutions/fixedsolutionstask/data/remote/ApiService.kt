package com.fixedsolutions.fixedsolutionstask.data.remote

import com.fixedsolutions.fixedsolutionstask.MovieListResponse
import com.fixedsolutions.fixedsolutionstask.data.API_KEY
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("en/API/ComingSoon/${API_KEY}")
    suspend fun getComingSoon(): Response<MovieListResponse>


    @GET("en/API/InTheaters/${API_KEY}")
    suspend fun getInTheaters(): Response<MovieListResponse>

}