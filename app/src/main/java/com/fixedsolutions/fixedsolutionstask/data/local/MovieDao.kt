package com.fixedsolutions.fixedsolutionstask.data.local

import androidx.room.*
import com.fixedsolutions.fixedsolutionstask.MovieItem


@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieItem(movie : MovieItem) : Long

    @Query("SELECT * FROM movie WHERE movieType = :movieType")
    suspend fun getMovieItems(movieType: String): List<MovieItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieItems(movie: List<MovieItem?>) : List<Long>

    @Query("SELECT * FROM movie")
    suspend fun getMovieItems(): List<MovieItem>

    @Update
    suspend fun updateMovieItem(movie: MovieItem): Int

    @Query("DELETE FROM movie WHERE roomId = :roomId")
    suspend fun deleteMovieItemById(roomId : Int) : Int

    @Delete
    suspend fun deleteMovieItem(movie : MovieItem) : Int

    @Query("DELETE FROM movie")
    suspend fun clearCachedMovieItems(): Int
}