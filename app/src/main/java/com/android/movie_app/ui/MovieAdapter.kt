package com.android.movie_app.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.movie_app.R
import com.android.movie_app.callbacks.DiffUtilCallBack
import com.android.movie_app.data.Movies
import com.android.movie_app.databinding.AdapterMovieBinding
import kotlin.properties.Delegates

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MyViewHolder>(), DiffUtilCallBack {

    var items: List<Movies> by Delegates.observable(emptyList()) { _, oldItem, newItem ->
        autoNotify(oldItem, newItem) { old, new -> old.movieId == new.movieId }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.MyViewHolder {
        val binding: AdapterMovieBinding = DataBindingUtil
            .inflate(LayoutInflater.from(parent.context), R.layout.adapter_movie, parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieAdapter.MyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class MyViewHolder(private val binding: AdapterMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movies: Movies) {
            binding.movies = movies
        }
    }
}