package com.demo.movieapp.main.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.demo.movieapp.BuildConfig
import com.demo.movieapp.common.base.BaseViewHolder
import com.demo.movieapp.common.listener.OnItemClicked
import com.demo.movieapp.common.model.Movie
import com.demo.movieapp.common.util.loadFrom
import com.demo.movieapp.databinding.ItemLayoutMovieBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {


    private val dataList = arrayListOf<Movie>()

    private var listener: OnItemClicked? = null

    fun setListener(onItemClicked: OnItemClicked?) {
        listener = onItemClicked
    }

    fun updateData(data: List<Movie>) {

        val diffUtil = MovieDiffUtil(dataList, data)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        dataList.apply {
            clear()
            addAll(data)
        }
        diffResults.dispatchUpdatesTo(this)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            ItemLayoutMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    inner class MovieViewHolder(private val layout: ItemLayoutMovieBinding) :
        BaseViewHolder<Movie>(layout.root) {
        private lateinit var movie: Movie

        init {
            layout.root.setOnClickListener {
                if (::movie.isInitialized)
                    listener?.onClick(movie)
            }


        }

        override fun bind(item: Movie) {
            movie = item
            layout.image.loadFrom(item.posterPath)
            layout.movieTitle.text = item.title
        }
    }
}