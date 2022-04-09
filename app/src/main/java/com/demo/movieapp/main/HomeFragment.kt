package com.demo.movieapp.main

import android.view.View
import androidx.fragment.app.viewModels
import com.demo.movieapp.common.base.BaseFragmentMVVM
import com.demo.movieapp.common.listener.OnItemClicked
import com.demo.movieapp.common.model.Movie
import com.demo.movieapp.common.network.CommonStatus
import com.demo.movieapp.common.network.IViewState
import com.demo.movieapp.common.network.ResponseWrapper
import com.demo.movieapp.common.util.DialogUtils
import com.demo.movieapp.common.util.navigateSafe
import com.demo.movieapp.common.util.observe
import com.demo.movieapp.databinding.FragmentHomeBinding
import com.demo.movieapp.main.list.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : BaseFragmentMVVM<FragmentHomeBinding, HomeViewModel>(), OnItemClicked {

    @Inject
    lateinit var movieAdapter: MovieAdapter

    override fun getViewBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    override fun initViewModel(): Lazy<HomeViewModel> = viewModels()

    override fun onCreateInit() {
        // tide adapter to view
        binding.moveRec.adapter = movieAdapter
        movieAdapter.setListener(this)
        getInitViewModel().getMovieFromApi()
        initObserver()
    }


    private fun initObserver(){
        observe(getInitViewModel().getMovieFromDbObserver(), ::movieObserver)
        observe(getInitViewModel().getMovieObserver(),::loadDataObserver)
    }

    private fun movieObserver(data: List<Movie>?) {
        data?.let { movieAdapter.updateData(it) }
    }


    private fun loadDataObserver(it:IViewState<ResponseWrapper<List<Movie>>>?){
        when(it?.whichState()){
            CommonStatus.LOADING-> {
                binding.loadingProgress.visibility=View.VISIBLE
            }
            CommonStatus.SUCCESS-> {
                binding.loadingProgress.visibility=View.GONE
            }
            CommonStatus.ERROR-> {
                binding.loadingProgress.visibility=View.GONE
                DialogUtils.showGenericErrorPopup(
                    context = requireActivity(),
                    retryListener = {
                        getInitViewModel().getMovieFromApi()
                    },
                    cancelListener = {

                    },
                    isCancelable = false,
                    exception=it.fetchError()
                )
            }
        }
    }

    override fun <T> onClick(item: T) {
        when (item) {
            is Movie-> navigateSafe(HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(
                movie = item
            ))
        }
    }

}
