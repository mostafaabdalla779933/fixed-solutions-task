package com.fixedsolutions.fixedsolutionstask.ui.home.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.fixedsolutions.fixedsolutionstask.MovieItem
import com.fixedsolutions.fixedsolutionstask.R
import com.fixedsolutions.fixedsolutionstask.databinding.FragmentHomeBinding
import com.fixedsolutions.fixedsolutionstask.ui.home.adapters.MoviesAdapter
import com.fixedsolutions.fixedsolutionstask.ui.home.state.HomeState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding:FragmentHomeBinding
    private lateinit var viewModel: HomeVM
    private val comingSoonAdapter : MoviesAdapter by lazy {
        MoviesAdapter {

        }
    }

    private val inTheatersAdapter : MoviesAdapter by lazy {
        MoviesAdapter {

        }
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

    private fun handleState(state: HomeState){

        when(state){
            is HomeState.ComingSoonState -> {
                handleComingSoonState(state)
            }
            is HomeState.InTheatersState -> {
                handleInTheatersState(state)
            }
            else ->{}
        }
    }

    private fun handleComingSoonState(state: HomeState.ComingSoonState) {
        if (state.isLoading) {
            comingSoonAdapter.setMovieList((1..5).map { MovieItem(isShimmer = true) })
        } else if (state.movies.isEmpty()) {

        } else {
            comingSoonAdapter.setMovieList(state.movies)
        }
    }

    private fun handleInTheatersState(state: HomeState.InTheatersState) {
        if (state.isLoading) {
            inTheatersAdapter.setMovieList((1..5).map { MovieItem(isShimmer = true) })
        } else if (state.movies.isEmpty()) {

        } else {
            inTheatersAdapter.setMovieList(state.movies)
        }
    }

    private fun initView(){
        binding.apply {
            rvComingSoon.adapter = comingSoonAdapter
            rvInTheaters.adapter = inTheatersAdapter
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.onClear()
    }
}