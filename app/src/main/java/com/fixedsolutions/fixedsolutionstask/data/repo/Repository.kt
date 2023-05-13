package com.fixedsolutions.fixedsolutionstask.data.repo

import com.fixedsolutions.fixedsolutionstask.MovieItem
import com.fixedsolutions.fixedsolutionstask.MovieListResponse
import com.fixedsolutions.fixedsolutionstask.MovieType
import com.fixedsolutions.fixedsolutionstask.data.local.MovieDao
import com.fixedsolutions.fixedsolutionstask.data.remote.ApiService
import com.fixedsolutions.fixedsolutionstask.domain.repo.IRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: ApiService,
    private val movieDAO: MovieDao
) : IRepository {

    override suspend fun getComingSoon(fromCache: Boolean): Flow<Response<MovieListResponse>> {
        return flow {
            if (fromCache) {
                val cache = movieDAO.getMovieItems(MovieType.ComingSoon.value)
                if (cache.isEmpty()) {
                    val response = apiService.getComingSoon()
                    movieDAO.addMovieItems(mapResponse(response,MovieType.ComingSoon.value))
                    emit(response)
                } else {
                    emit(Response.success(MovieListResponse(items = cache)))
                }
            } else {
                val response = apiService.getComingSoon()
                movieDAO.addMovieItems(mapResponse(response,MovieType.ComingSoon.value))
                emit(response)
            }
        }
    }

    override suspend fun getInTheaters(fromCache: Boolean): Flow<Response<MovieListResponse>> {
        return flow { emit(apiService.getInTheaters()) }
    }

    override suspend fun getTopRatedMovies(fromCache: Boolean): Flow<Response<MovieListResponse>> {
        return flow { emit(apiService.getTopRatedMovies()) }
    }

    override suspend fun getBoxOffice(fromCache: Boolean): Flow<Response<MovieListResponse>> {
        return flow { emit(apiService.getBoxOffice()) }
    }


    fun mapResponse(response: Response<MovieListResponse>,movieType:String):List<MovieItem?>{
        return response.body()?.items?.map {
            it?.also {
                it.movieType = movieType
            }
        } ?: emptyList()

    }
}