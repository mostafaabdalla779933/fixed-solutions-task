package com.fixedsolutions.fixedsolutionstask.di

import android.content.Context
import androidx.room.Room
import com.fixedsolutions.fixedsolutionstask.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Module that holds database related classes
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     * Provides [AppDatabase] instance
     */
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, "database")
            .build()
    }

    /**
     * Provides [MovieDAO] instance
     */
    @Provides
    @Singleton
    fun provideMovieDAO(appDatabase: AppDatabase) = appDatabase.movieDao()
}