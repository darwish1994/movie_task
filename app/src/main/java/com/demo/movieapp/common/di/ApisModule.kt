package com.demo.movieapp.common.di

import com.demo.movieapp.common.network.calls.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class ApisModule {

    @Provides
    fun provideMovieApiCalls(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }

}