package com.fixedsolutions.fixedsolutionstask.domain.usecase


import com.fixedsolutions.fixedsolutionstask.MovieListResponse
import com.fixedsolutions.fixedsolutionstask.domain.repo.IRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject


@ViewModelScoped
class HomeUseCase @Inject constructor(val repository: IRepository)  {


    suspend fun getComingSoon(fromCache: Boolean): Flow<Response<MovieListResponse>> {
        return repository.getComingSoon(fromCache)
    }

    suspend fun getInTheaters(fromCache: Boolean): Flow<Response<MovieListResponse>> {
        return repository.getInTheaters(fromCache)
    }

    suspend fun getTopRatedMovies(fromCache: Boolean): Flow<Response<MovieListResponse>> {
        return repository.getTopRatedMovies(fromCache)
    }

    suspend fun getBoxOffice(fromCache: Boolean): Flow<Response<MovieListResponse>> {
        return repository.getBoxOffice(fromCache)
    }



}