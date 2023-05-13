package com.fixedsolutions.fixedsolutionstask.domain


import com.fixedsolutions.fixedsolutionstask.ComingSoonResponse
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject


@ViewModelScoped
class HomeUseCase @Inject constructor(val repository: IRepository):IHomeUseCase{
    override suspend fun getComingSoon(): Flow<Response<ComingSoonResponse>> {
        return repository.getComingSoon()
    }


}