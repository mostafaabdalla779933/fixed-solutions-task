package com.fixedsolutions.fixedsolutionstask.ui.home.state

import com.fixedsolutions.fixedsolutionstask.MovieItem

sealed class HomeState {

    class ComingSoonState(
        val isLoading: Boolean = true,
        val movies: List<MovieItem?> = emptyList(),
        val error: String? = null
    ) : HomeState()


    class InTheatersState(
        val isLoading: Boolean = true,
        val movies: List<MovieItem?> = emptyList(),
        val error: String? = null
    ) : HomeState()

    class TopRatedMoviesState(
        val isLoading: Boolean = true,
        val movies: List<MovieItem?> = emptyList(),
        val error: String? = null
    ) : HomeState()


    class BoxOfficeState(
        val isLoading: Boolean = true,
        val movies: List<MovieItem?> = emptyList(),
        val error: String? = null
    ) : HomeState()


}