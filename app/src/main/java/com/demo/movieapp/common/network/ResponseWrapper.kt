package com.demo.movieapp.common.network

import com.google.gson.annotations.SerializedName

open class ResponseWrapper<T>(
    @SerializedName("page")
    val page: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("results")
    var data: T)
