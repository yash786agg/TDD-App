package com.android.movie_app.repository

import com.android.movie_app.data.MovieResult

interface MovieRepository {
    suspend fun getMovieRepo(): MovieResult
}
