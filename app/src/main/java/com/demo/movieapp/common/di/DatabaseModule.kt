package com.demo.movieapp.common.di

import android.content.Context
import androidx.room.Room
import com.demo.movieapp.common.database.MovieDatabase
import com.demo.movieapp.common.database.SharedData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, MovieDatabase::class.java, "MovieDatabase").build()


    @Provides
    @Singleton
    fun injectMovieDao(database: MovieDatabase) = database.movieDoa()

    @Provides
    @Singleton
    fun provideShareData(@ApplicationContext context: Context) = SharedData(context)

}