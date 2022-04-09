package com.demo.movieapp.common.util

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.demo.movieapp.BuildConfig
import com.demo.movieapp.R

fun ImageView.loadFrom(
    url: String?,
    @DrawableRes placeHolderImage: Int = R.drawable.ic_splash_logo,
    @DrawableRes errorImage: Int = R.drawable.ic_splash_logo
) {
    if (url.isNullOrBlank())
        setImageResource(errorImage)
    else
        Glide.with(this)
            .load(BuildConfig.IMAGE_URL.plus(url))
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(errorImage)
            .into(this)
}