package com.android.movie_app.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.movie_app.data.MovieResult
import com.android.movie_app.repository.MovieRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieActivityVM(
    private val movieRepository: MovieRepositoryImpl
    ) : ViewModel() {


    private val _movieResult: MutableLiveData<MovieResult> = MutableLiveData<MovieResult>()
    private val movieResult: LiveData<MovieResult> = _movieResult

    fun movieLiveData(): LiveData<MovieResult> {
        return movieResult
    }

    fun getMovies() {

        _movieResult.value = MovieResult.Loading(true)

        viewModelScope.launch(Dispatchers.IO) {
            val response = movieRepository.getMovieRepo()

            withContext(Dispatchers.Main) {
                _movieResult.value = response
                _movieResult.value = MovieResult.Loading(false)
            }
        }
    }

}
