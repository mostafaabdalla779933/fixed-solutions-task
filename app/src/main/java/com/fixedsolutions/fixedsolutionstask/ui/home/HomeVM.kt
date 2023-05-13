package com.fixedsolutions.fixedsolutionstask.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fixedsolutions.fixedsolutionstask.common.handleError
import com.fixedsolutions.fixedsolutionstask.domain.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeVM @Inject constructor(val useCase: HomeUseCase) : ViewModel() {


    private val jobs = mutableListOf<Job>()
    val state = MutableStateFlow<HomeState>(HomeState.ComingSoonState())

    fun getScreenData() {
        viewModelScope.launch {
            useCase.getComingSoon()
                .onStart {
                    state.value = HomeState.ComingSoonState(isLoading = true)
                }.onEach { response ->
                    if (response.isSuccessful) {
                        response.body()?.let {
                            state.value = HomeState.ComingSoonState(
                                isLoading = false,
                                movies = it.items ?: emptyList()
                            )
                        } ?: kotlin.run {
                            state.value = HomeState.ComingSoonState(
                                isLoading = false,
                                error = "Something Went Wrong"
                            )
                        }
                    }
                }.catch {
                    state.value =
                        HomeState.ComingSoonState(isLoading = false, error = handleError(it))
                }.launchIn(viewModelScope).also { jobs.add(it) }
        }
    }


    fun onClear(){
        jobs.forEach { job ->
            if (job.isActive)
                job.cancel()
        }
    }

}