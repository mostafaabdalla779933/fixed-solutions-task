package com.fixedsolutions.fixedsolutionstask

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Parcelize
data class MovieListResponse(

    @field:SerializedName("errorMessage")
    val errorMessage: String? = null,

    @field:SerializedName("items")
    val items: List<MovieItem?>? = null
) : Parcelable

@Parcelize
data class GenreListItem(

    @field:SerializedName("value")
    val value: String? = null,

    @field:SerializedName("key")
    val key: String? = null
) : Parcelable

@Parcelize
data class StarListItem(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: String? = null
) : Parcelable

@Entity(tableName = "movie")
@Parcelize
data class MovieItem(
    @PrimaryKey(autoGenerate = true)
    var roomId: Int = 0,

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("fullTitle")
    val fullTitle: String? = null,

    @field:SerializedName("runtimeMins")
    val runtimeMins: String? = null,

    @field:SerializedName("year")
    val year: String? = null,

    @field:SerializedName("directors")
    val directors: String? = null,

    @field:SerializedName("metacriticRating")
    val metacriticRating: String? = null,

    @field:SerializedName("stars")
    val stars: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("imDbRating")
    val imDbRating: String? = null,

    @field:SerializedName("runtimeStr")
    val runtimeStr: String? = null,

    @field:SerializedName("imDbRatingCount")
    val imDbRatingCount: String? = null,

    @field:SerializedName("plot")
    val plot: String? = null,

    @field:SerializedName("genres")
    val genres: String? = null,

    @field:SerializedName("contentRating")
    val contentRating: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("releaseState")
    val releaseState: String? = null,

    var isShimmer: Boolean = false,
    var movieType: String? = null
) : Parcelable


enum class MovieType(val value: String) {
    ComingSoon("ComingSoon"),
    InTheaters("InTheaters"),
    TopRated("TopRated"),
    HighGrossing("HighGrossing")
}

