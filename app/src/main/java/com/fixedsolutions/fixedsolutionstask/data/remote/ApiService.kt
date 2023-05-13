package com.fixedsolutions.fixedsolutionstask.data.remote

import com.fixedsolutions.fixedsolutionstask.ComingSoonResponse
import com.fixedsolutions.fixedsolutionstask.data.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("en/API/ComingSoon/${API_KEY}")
    suspend fun getComingSoon(): Response<ComingSoonResponse>

}