package com.demo.movieapp.common.database

import android.content.Context
import android.content.SharedPreferences
import com.demo.movieapp.common.util.Constant
import com.demo.movieapp.common.util.addHours
import java.util.*
import javax.inject.Inject

class SharedData @Inject constructor(private val context: Context) {
    private fun getSharedPreferences(): SharedPreferences = context.getSharedPreferences(Constant.SHARED_KEY, Context.MODE_PRIVATE)

    fun setLastSync() = getSharedPreferences().edit().putLong(Constant.LAST_SYNC, Date().time).apply()

    fun getLastSync() = getSharedPreferences().getLong(Constant.LAST_SYNC, Date().addHours(- 4).time)

}