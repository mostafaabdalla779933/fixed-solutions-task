package com.fixedsolutions.fixedsolutionstask.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.fixedsolutions.fixedsolutionstask.common.showMessage
import com.fixedsolutions.fixedsolutionstask.data.model.ResultsItem
import com.fixedsolutions.fixedsolutionstask.databinding.FragmentSearchBinding
import com.fixedsolutions.fixedsolutionstask.ui.search.state.SearchState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchVM
    private val resultAdapter: ResultsAdapter by lazy {
        ResultsAdapter {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[SearchVM::class.java]
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        lifecycleScope.launch {
            viewModel.state.collect {
                handleState(it)
            }
        }

    }

    private fun handleState(state: SearchState) {
        if (state.isLoading) {
            resultAdapter.setResultsList((1..5).map { ResultsItem(isShimmer = true) })
        } else if (state.error != null) {
            showMessage(state.error)
            resultAdapter.setResultsList(emptyList())
        } else {
            resultAdapter.setResultsList(state.results)
        }
    }

    private fun initView() {
        binding.apply {
            sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if(query.isNullOrEmpty().not()){
                        viewModel.onClear()
                        viewModel.searchExpression(query?.trim() ?: "")
                    }
                    return true
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            })

            rvSearchResult.adapter = resultAdapter
        }
    }
}