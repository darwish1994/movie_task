package com.demo.movieapp.common.base

import android.app.Application
import androidx.viewbinding.BuildConfig
import com.demo.movieapp.common.database.MovieDao
import com.demo.movieapp.common.database.MovieDatabase
import com.demo.movieapp.common.database.SharedData
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class BaseApplication :Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }


    }
}