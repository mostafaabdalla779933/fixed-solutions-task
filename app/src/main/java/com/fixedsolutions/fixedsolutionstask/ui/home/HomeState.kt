package com.fixedsolutions.fixedsolutionstask.ui.home

import com.fixedsolutions.fixedsolutionstask.MovieItem

sealed class HomeState {

    class ComingSoonState(
        val isLoading: Boolean = true,
        val movies: List<MovieItem?> = emptyList(),
        val error: String? = null
    ) : HomeState()
}