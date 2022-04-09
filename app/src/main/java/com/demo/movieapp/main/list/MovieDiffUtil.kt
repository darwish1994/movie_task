package com.demo.movieapp.main.list

import androidx.recyclerview.widget.DiffUtil
import com.demo.movieapp.common.model.Movie

class MovieDiffUtil(private val oldList: List<Movie>, private val newList: List<Movie>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        return when {
            oldList[oldItemPosition].title != newList[newItemPosition].title -> false
            oldList[oldItemPosition].voteAverage != newList[newItemPosition].voteAverage -> false
            oldList[oldItemPosition].video != newList[newItemPosition].video -> false
            oldList[oldItemPosition].overview != newList[newItemPosition].overview-> false
            else -> true


        }
    }
}