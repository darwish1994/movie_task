package com.demo.movieapp.common.di

import com.demo.movieapp.main.list.MovieAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
class AdapterModule {

    @Provides
    fun provideMovieAdapter() = MovieAdapter()
}