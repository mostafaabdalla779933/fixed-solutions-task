package com.fixedsolutions.fixedsolutionstask.domain.usecase


import com.fixedsolutions.fixedsolutionstask.MovieListResponse
import com.fixedsolutions.fixedsolutionstask.domain.repo.IRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject


@ViewModelScoped
class HomeUseCase @Inject constructor(val repository: IRepository)  {


    suspend fun getComingSoon(): Flow<Response<MovieListResponse>> {
        return repository.getComingSoon()
    }

    suspend fun getInTheaters(): Flow<Response<MovieListResponse>> {
        return repository.getInTheaters()
    }

    suspend fun getTopRatedMovies(): Flow<Response<MovieListResponse>> {
        return repository.getTopRatedMovies()
    }



}