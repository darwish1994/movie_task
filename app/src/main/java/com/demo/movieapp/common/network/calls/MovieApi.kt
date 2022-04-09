package com.demo.movieapp.common.network.calls

import com.demo.movieapp.common.model.Movie
import com.demo.movieapp.common.network.ResponseWrapper
import retrofit2.http.GET

interface MovieApi {
    @GET("movie/popular")
    suspend fun getPopularMovie(): ResponseWrapper<List<Movie>>

}