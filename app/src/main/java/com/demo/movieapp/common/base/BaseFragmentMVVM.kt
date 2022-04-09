package com.demo.movieapp.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseFragmentMVVM<VB : ViewBinding, VM : ViewModel>() : BaseFragment<VB>() {

    protected lateinit var viewModel: Lazy<VM>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = initViewModel()

    }
    override fun onResume() {
        super.onResume()
        onCreateInit()
    }

    abstract fun initViewModel(): Lazy<VM>

    abstract fun onCreateInit()
    fun getInitViewModel() = viewModel.value

}