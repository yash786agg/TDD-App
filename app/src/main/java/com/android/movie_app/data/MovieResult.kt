package com.android.movie_app.data

sealed class MovieResult {

    data class Success(val movieResponse: MovieResponse) : MovieResult()
    data class Loading(val enableLoading: Boolean) : MovieResult()
    data class Error(val errorMsg: String): MovieResult()
}
