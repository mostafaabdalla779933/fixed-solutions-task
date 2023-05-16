package com.fixedsolutions.fixedsolutionstask

import androidx.lifecycle.viewModelScope
import com.fixedsolutions.fixedsolutionstask.common.testFlowObserver
import com.fixedsolutions.fixedsolutionstask.domain.usecase.HomeUseCase
import com.fixedsolutions.fixedsolutionstask.ui.home.state.HomeState
import com.fixedsolutions.fixedsolutionstask.ui.home.view.HomeVM
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.Rule

import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import retrofit2.Response
import java.util.concurrent.CountDownLatch


@ExperimentalCoroutinesApi
class HomeVMTest {


    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()


    @Test
    fun `test getScreenData`() = runBlockingTest {
        // Mock dependencies
        val useCase = mockk<HomeUseCase>()
        val homeVM = HomeVM(useCase)

        // Prepare data
        val comingSoonResponse = Response.success(MovieListResponse(items = listOf(mockk())))
        val inTheatersResponse = Response.success(MovieListResponse(items = listOf(mockk())))
        val topRatedMoviesResponse = Response.success(MovieListResponse(items = listOf(mockk())))
        val boxOfficeResponse = Response.success(MovieListResponse(items = listOf(mockk())))

        coEvery { useCase.getComingSoon(true) } returns flowOf(comingSoonResponse)
        coEvery { useCase.getInTheaters(true) } returns flowOf(inTheatersResponse)
        coEvery { useCase.getTopRatedMovies(true) } returns flowOf(topRatedMoviesResponse)
        coEvery { useCase.getBoxOffice(true) } returns flowOf(boxOfficeResponse)

        // Create FlowTestObserver for each state
        val comingSoonObserver =  homeVM.state.testFlowObserver(mainCoroutineRule)
        val inTheatersObserver = homeVM.state.testFlowObserver(mainCoroutineRule)
        val topRatedMoviesObserver = homeVM.state.testFlowObserver(mainCoroutineRule)
        val boxOfficeObserver = homeVM.state.testFlowObserver(mainCoroutineRule)

        // Execute
        homeVM.getScreenData()

        // Collect and verify each state
        val comingSoonState = comingSoonObserver.awaitValue()
        assertEquals(HomeState.ComingSoonState(movies = comingSoonResponse.body()?.items ?: emptyList()), comingSoonState)

        val inTheatersState = inTheatersObserver.awaitValue()
        assertEquals(HomeState.InTheatersState(movies = inTheatersResponse.body()?.items ?: emptyList()), inTheatersState)

        val topRatedMoviesState = topRatedMoviesObserver.awaitValue()
        assertEquals(HomeState.TopRatedMoviesState(movies = topRatedMoviesResponse.body()?.items ?: emptyList()), topRatedMoviesState)

        val boxOfficeState = boxOfficeObserver.awaitValue()
        assertEquals(HomeState.BoxOfficeState(movies = boxOfficeResponse.body()?.items ?: emptyList()), boxOfficeState)
    }
}


@ExperimentalCoroutinesApi
class MainCoroutineRule(
    val dispatcher: CoroutineDispatcher = TestCoroutineDispatcher()
) : TestWatcher(), TestCoroutineScope by TestCoroutineScope(dispatcher) {

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        cleanupTestCoroutines()
        Dispatchers.resetMain()
    }
}




