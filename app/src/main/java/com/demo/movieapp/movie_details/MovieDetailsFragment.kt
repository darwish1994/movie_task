package com.demo.movieapp.movie_details

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.demo.movieapp.R
import com.demo.movieapp.common.base.BaseFragment
import com.demo.movieapp.common.util.loadFrom
import com.demo.movieapp.databinding.FragmentMovieDetailsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding>() {

    private val args by navArgs<MovieDetailsFragmentArgs>()

    override fun getViewBinding(): FragmentMovieDetailsBinding = FragmentMovieDetailsBinding.inflate(layoutInflater)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args.movie.let {
            binding.image.loadFrom(it.backdropPath)
            binding.title.text=it.title
            binding.details.text=it.overview
            binding.rate.text=getString(R.string.rate,it.voteAverage.toString())

        }

    }


}
