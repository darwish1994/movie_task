package com.demo.movieapp.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.movieapp.common.model.Movie
import com.demo.movieapp.common.repo.MovieRepo
import com.demo.movieapp.common.util.SingleLiveEvent
import com.demo.movieapp.common.network.IViewState
import com.demo.movieapp.common.network.ResponseWrapper
import com.demo.movieapp.common.network.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieRepo: MovieRepo) : ViewModel() {
    private val movieListLiveData by lazy { SingleLiveEvent<IViewState<ResponseWrapper<List<Movie>>>>() }
    private val movieListLiveDataWithDb by lazy { movieRepo.getMoveFrmDataBase() }

    fun getMovieObserver() = movieListLiveData

    fun getMovieFromDbObserver() = movieListLiveDataWithDb


    fun getMovieFromApi() {
        movieListLiveData.value = Result.loading()
        viewModelScope.launch {
            val result = movieRepo.getPopularMovie()
            movieListLiveData.postValue(result)

        }


    }

}