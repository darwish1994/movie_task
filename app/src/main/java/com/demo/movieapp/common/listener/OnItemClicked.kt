package com.demo.movieapp.common.listener

interface OnItemClicked {
    fun <T> onClick(item: T)
}