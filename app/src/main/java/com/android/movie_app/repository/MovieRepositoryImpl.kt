package com.android.movie_app.repository

import com.android.movie_app.api.MovieApi
import com.android.movie_app.data.MovieResult
import retrofit2.HttpException

class MovieRepositoryImpl(private val movieApi: MovieApi) : MovieRepository {
    override suspend fun getMovieRepo(): MovieResult {
        return try {
            val response = movieApi.getMovieAsync().await()
            MovieResult.Success(response)
        }catch (exception: HttpException) {
            MovieResult.Error("No Data Found")
        }
    }

}
