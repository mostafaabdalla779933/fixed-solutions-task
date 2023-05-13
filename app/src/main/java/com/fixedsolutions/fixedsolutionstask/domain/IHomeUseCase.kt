package com.fixedsolutions.fixedsolutionstask.domain

import com.fixedsolutions.fixedsolutionstask.ComingSoonResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface IHomeUseCase {

    suspend fun getComingSoon(): Flow<Response<ComingSoonResponse>>
}