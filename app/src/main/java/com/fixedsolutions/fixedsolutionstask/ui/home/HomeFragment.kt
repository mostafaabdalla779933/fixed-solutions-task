package com.fixedsolutions.fixedsolutionstask.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.fixedsolutions.fixedsolutionstask.R
import com.fixedsolutions.fixedsolutionstask.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var binding:FragmentHomeBinding
    lateinit var viewModel:HomeVM
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[HomeVM::class.java]



        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.state.collect{
                handleState(it)
            }
        }
        viewModel.getScreenData()
    }

    private fun handleState(state:HomeState){

        when(state){
            is HomeState.ComingSoonState -> {
                handleComingSoonState(state)
            }
            else ->{}
        }
    }

    private fun handleComingSoonState(state: HomeState.ComingSoonState){

    }


    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.onClear()
    }
}