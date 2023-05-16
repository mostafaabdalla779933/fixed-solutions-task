package com.fixedsolutions.fixedsolutionstask

import com.fixedsolutions.fixedsolutionstask.data.local.MovieDao
import com.fixedsolutions.fixedsolutionstask.data.remote.ApiService
import com.fixedsolutions.fixedsolutionstask.data.repo.Repository
import io.mockk.*
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class RepositoryTest {

    private lateinit var repository: Repository
    private lateinit var apiService: ApiService
    private lateinit var movieDAO: MovieDao

    @Before
    fun setUp() {
        apiService = mockk(relaxed = true)
        movieDAO = mockk(relaxed = true)
        repository = Repository(apiService, movieDAO, TestCoroutineDispatcher())
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun getComingSoon_should_return_data_from_cache_when_available() = runBlocking {

        val cacheData = listOf(MovieItem("Movie 1"), MovieItem("Movie 2"))
        coEvery { movieDAO.getMovieItems(MovieType.ComingSoon.value) } returns cacheData
        coEvery { apiService.getComingSoon() } returns Response.success(MovieListResponse())

        val flow: Flow<Response<MovieListResponse>> = repository.getComingSoon(fromCache = true)

        flow.collect { response ->
            assert(response.isSuccessful)
            assert(response.body()?.items == cacheData)
        }

        coVerify (exactly = 1) {
            movieDAO.getMovieItems(MovieType.ComingSoon.value)
        }
        coVerify(exactly = 0) { apiService.getComingSoon() }
    }



    @Test
    fun getComingSoon_should_return_data_from_api_and_cache_it() = runBlocking {

        val movieItems = listOf(MovieItem("Movie 1"), MovieItem("Movie 2"))
        val apiResponse =Response.success(MovieListResponse(items = movieItems ))


        coEvery { apiService.getComingSoon() } returns apiResponse
        coEvery { movieDAO.getMovieItems(MovieType.ComingSoon.value) } returns emptyList()
        coEvery { movieDAO.addMovieItems(movieItems) } returns listOf()

        val flow = repository.getComingSoon(false)


        flow.collect { response ->
            assert(response.isSuccessful)
            assert(response.body()?.items == movieItems)
        }

        coVerify(exactly = 0) { movieDAO.getMovieItems(MovieType.ComingSoon.value) }
        coVerify { apiService.getComingSoon() }
        coVerify { movieDAO.deleteMovieItemByMovieType(MovieType.ComingSoon.value) }
        coVerify { movieDAO.addMovieItems(movieItems) }
    }


}
