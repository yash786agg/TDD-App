package com.android.movie_app.api

import com.android.movie_app.data.MovieResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface MovieApi {
    @GET("movie/popular?api_key=your_api_key&language=en-US&page=1")
    fun getMovieAsync(): Deferred<MovieResponse>
}
