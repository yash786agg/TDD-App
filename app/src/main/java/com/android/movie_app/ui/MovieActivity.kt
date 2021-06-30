package com.android.movie_app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.android.movie_app.data.MovieResult
import com.android.movie_app.databinding.ActivityMovieBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieActivity : AppCompatActivity() {

    private val TAG: String = "MovieActivity"
    private val movieActivityVM: MovieActivityVM by viewModel()
    private lateinit var activityMovieBinding: ActivityMovieBinding
    private val movieAdapter = MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMovieBinding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(activityMovieBinding.root) // activity_movie.xml

        initialiseRecyclerView()

        movieActivityVM.getMovies()
        movieActivityVM.movieLiveData().observe(this,{

            when(it) {
                is MovieResult.Error -> Log.e(TAG," MovieResult Error: $it")
                is MovieResult.Loading -> Log.e(TAG," MovieResult Loading: $it")
                is MovieResult.Success -> {
                    Log.e(TAG," MovieResult Success Size: ${it.movieResponse.results.size}")
                    Log.e(TAG," MovieResult Success: $it")

                    if(it.movieResponse.results.isNotEmpty()) {
                        movieAdapter.items = it.movieResponse.results
                    }
                }
            }
        })
    }

    private fun initialiseRecyclerView() {
        activityMovieBinding.recyclerView.layoutManager = GridLayoutManager(this,2)
        activityMovieBinding.recyclerView.adapter = movieAdapter
    }
}