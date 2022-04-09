package com.demo.movieapp.splash

import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.demo.movieapp.common.base.BaseActivity
import com.demo.movieapp.databinding.ActivitySplashBinding
import com.demo.movieapp.MainActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    private val handler by lazy { Handler(Looper.getMainLooper()) }
    private val runnable by lazy {
        Runnable {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun getViewBinding(): ActivitySplashBinding =
        ActivitySplashBinding.inflate(layoutInflater)

    override fun initOnCreate() {
        handler.postDelayed(runnable, 2000)
    }

    override fun onDestroy() {
        handler.removeCallbacks(runnable)
        super.onDestroy()

    }
}