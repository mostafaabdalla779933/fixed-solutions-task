package com.fixedsolutions.fixedsolutionstask

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fixedsolutions.fixedsolutionstask.data.local.AppDatabase
import com.fixedsolutions.fixedsolutionstask.data.local.MovieDao
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException



@RunWith(AndroidJUnit4::class)
class TestMovieDao {
    private lateinit var movieDao: MovieDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java).allowMainThreadQueries().build()
        movieDao = db.movieDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }


    @Test
    fun insert_Movie_And_Read_It_From_The_List() = runBlocking {
        val movie =  MovieItem(title = "title", fullTitle = "fullTitle", id = "100")
        movieDao.addMovieItem(movie)
        assert(movieDao.getMovieItems().contains(movie))
    }

    @Test
    fun insert_List_Of_Movies_And_Read_It_From_The_List() = runBlocking {

        val moviesList = listOf(MovieItem(title = "title", fullTitle = "fullTitle",imDbRating = "imDbRating", id = "2"),
            MovieItem(title = "title2", fullTitle = "fullTitle2",imDbRating = "imDbRating2", id ="3" ))
        movieDao.addMovieItems(moviesList)
        movieDao.getMovieItems().let{
            assert(it.containsAll(moviesList))
        }
    }


    @Test
    fun clear_Table() = runBlocking {
        movieDao.clearCachedMovieItems()
        assert(movieDao.getMovieItems().isEmpty())
    }

    @Test
    fun test_Conflict_Add_Two_Item_With_The_Same_Id(): Unit = runBlocking{
        val movies = listOf(MovieItem(title = "title1", id = "1"),
            MovieItem(title = "title2",id = "1"))

        movieDao.addMovieItems(movies)
        movieDao.getMovieItems().let{
            assert(!it.contains(movies[0]))
            assert(it.contains(movies[1]))
        }
    }
}