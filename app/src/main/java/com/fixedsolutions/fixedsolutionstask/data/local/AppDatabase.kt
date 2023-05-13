package com.fixedsolutions.fixedsolutionstask.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fixedsolutions.fixedsolutionstask.MovieItem

@Database(entities = [MovieItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao() : MovieDao
}