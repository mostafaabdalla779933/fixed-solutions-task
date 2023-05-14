package com.fixedsolutions.fixedsolutionstask.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fixedsolutions.fixedsolutionstask.common.handleError
import com.fixedsolutions.fixedsolutionstask.domain.usecase.SearchUserCase
import com.fixedsolutions.fixedsolutionstask.ui.search.state.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchVM @Inject constructor(val useCase: SearchUserCase) : ViewModel() {


    private val jobs = mutableListOf<Job>()
    val state = MutableStateFlow(SearchState())

    fun searchExpression(expression: String) {
        viewModelScope.launch {
            useCase.searchExpression(expression)
                .onStart {
                    state.value = SearchState(isLoading = true)
                }.onEach { response ->
                    if (response.isSuccessful) {
                        response.body()?.let {
                            state.value = SearchState(
                                isLoading = false,
                                results = it.results ?: emptyList()
                            )
                        } ?: kotlin.run {
                            state.value = SearchState(
                                isLoading = false,
                                error = "Something Went Wrong"
                            )
                        }
                    }
                }.catch {
                    state.value =
                        SearchState(isLoading = false, error = handleError(it))
                }.launchIn(viewModelScope).also { jobs.add(it) }
        }
    }

    fun onClear() {
        jobs.forEach { job ->
            if (job.isActive)
                job.cancel()
        }
    }

}