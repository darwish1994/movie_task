package com.demo.movieapp.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: VB


    override fun onCreate(savedInstanceState: Bundle?) {
        onPreSetContentView()// for in case of animation
        super.onCreate(savedInstanceState)

        binding = getViewBinding()
        setContentView(binding.root)

        initOnCreate()

    }

    abstract fun getViewBinding(): VB






    open fun onPreSetContentView() {}
    protected abstract fun initOnCreate()
}