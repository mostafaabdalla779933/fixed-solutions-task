package com.fixedsolutions.fixedsolutionstask.data.repo

import com.fixedsolutions.fixedsolutionstask.ComingSoonResponse
import com.fixedsolutions.fixedsolutionstask.data.remote.ApiService
import com.fixedsolutions.fixedsolutionstask.domain.IRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val apiService : ApiService): IRepository {

    override suspend fun getComingSoon(): Flow<Response<ComingSoonResponse>> {
        return flow { emit(apiService.getComingSoon()) }
    }
}