package com.fixedsolutions.fixedsolutionstask.ui.home.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.fixedsolutions.fixedsolutionstask.MovieItem
import com.fixedsolutions.fixedsolutionstask.databinding.FragmentHomeBinding
import com.fixedsolutions.fixedsolutionstask.ui.home.adapters.MoviesAdapter
import com.fixedsolutions.fixedsolutionstask.ui.home.adapters.MoviesVerticalAdapter
import com.fixedsolutions.fixedsolutionstask.ui.home.state.HomeState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding:FragmentHomeBinding
    private lateinit var viewModel: HomeVM
    private val comingSoonAdapter : MoviesAdapter by lazy {
        MoviesAdapter {}
    }

    private val inTheatersAdapter : MoviesAdapter by lazy {
        MoviesAdapter {}
    }

    private val topRatedMoviesAdapter : MoviesAdapter by lazy {
        MoviesAdapter {}
    }

    private val highGrossingAdapter : MoviesVerticalAdapter by lazy {
        MoviesVerticalAdapter {}
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[HomeVM::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        lifecycleScope.launch {
            viewModel.state.collect{
                handleState(it)
            }
        }
        viewModel.getScreenData()
    }

    private fun handleState(state: HomeState) {
        when (state) {
            is HomeState.ComingSoonState -> {
                handleComingSoonState(state)
            }
            is HomeState.InTheatersState -> {
                handleInTheatersState(state)
            }
            is HomeState.TopRatedMoviesState -> {
                handleTopRatedMoviesState(state)
            }
            is HomeState.BoxOfficeState -> {
                handleBoxOfficeState(state)
            }
        }
    }

    private fun handleComingSoonState(state: HomeState.ComingSoonState) {
        binding.apply {
            if (state.isLoading) {
                comingSoonAdapter.setMovieList((1..5).map { MovieItem(isShimmer = true) })
            } else if (state.movies.isEmpty()) {
                showError(state.error)
            } else {
                comingSoonAdapter.setMovieList(state.movies)
            }
        }
    }

    private fun handleInTheatersState(state: HomeState.InTheatersState) {
        binding.apply {
            if (state.isLoading) {
                inTheatersAdapter.setMovieList((1..5).map { MovieItem(isShimmer = true) })
            } else if (state.movies.isEmpty()) {
                showError(state.error)
            } else {
                inTheatersAdapter.setMovieList(state.movies)
            }
        }

    }

    private fun handleTopRatedMoviesState(state: HomeState.TopRatedMoviesState) {
        binding.apply {
            if (state.isLoading) {
                topRatedMoviesAdapter.setMovieList((1..5).map { MovieItem(isShimmer = true) })
            } else if (state.movies.isEmpty()) {
                showError(state.error)
            } else {
                topRatedMoviesAdapter.setMovieList(state.movies)
            }
        }
    }

    private fun handleBoxOfficeState(state: HomeState.BoxOfficeState) {
        binding.apply {
            if (state.isLoading) {
                highGrossingAdapter.setMovieList((1..5).map { MovieItem(isShimmer = true) })
            } else if (state.movies.isEmpty()) {
                showError(state.error)
            } else {
                highGrossingAdapter.setMovieList(state.movies)
            }
        }
    }

    private fun showError(error: String?) {
        binding.apply {
            errorLayout.root.visibility = View.VISIBLE
            errorLayout.tvErrorMessage.text = error ?: "Something Went Wrong"
        }
    }

    private fun initView(){
        binding.apply {
            rvComingSoon.adapter = comingSoonAdapter
            rvInTheaters.adapter = inTheatersAdapter
            rvTopRatedMovies.adapter = topRatedMoviesAdapter
            rvHighGrossing.adapter = highGrossingAdapter

            srLayout.setOnRefreshListener {
                srLayout.isRefreshing = false
                errorLayout.root.visibility = View.GONE
                viewModel.refreshScreenData()
            }
            errorLayout.btnTryAgain.setOnClickListener {
                errorLayout.root.visibility = View.GONE
                viewModel.refreshScreenData()
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.onClear()
    }
}