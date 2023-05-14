package com.fixedsolutions.fixedsolutionstask.ui.search.state

import com.fixedsolutions.fixedsolutionstask.data.model.ResultsItem

class SearchState(
    val isLoading: Boolean = false,
    val results: List<ResultsItem?> = emptyList(),
    val error: String? = null
)