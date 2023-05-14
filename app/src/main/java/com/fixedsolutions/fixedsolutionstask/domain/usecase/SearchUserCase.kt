package com.fixedsolutions.fixedsolutionstask.domain.usecase

import com.fixedsolutions.fixedsolutionstask.MovieListResponse
import com.fixedsolutions.fixedsolutionstask.data.model.SearchResultResponse
import com.fixedsolutions.fixedsolutionstask.domain.repo.IRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject


@ViewModelScoped
class SearchUserCase @Inject constructor(val repository: IRepository)  {

    suspend fun searchExpression(expression: String): Flow<Response<SearchResultResponse>> {
        return repository.searchExpression(expression)
    }
}