package com.fixedsolutions.fixedsolutionstask.data.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class SearchResultResponse(

	@field:SerializedName("expression")
	val expression: String? = null,

	@field:SerializedName("searchType")
	val searchType: String? = null,

	@field:SerializedName("errorMessage")
	val errorMessage: String? = null,

	@field:SerializedName("results")
	val results: List<ResultsItem?>? = null
) : Parcelable

@Parcelize
data class ResultsItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("resultType")
	val resultType: String? = null,

	var isShimmer: Boolean = false
) : Parcelable
