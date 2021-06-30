package com.android.movie_app.ui

import com.android.movie_app.api.MovieApi
import com.android.movie_app.data.MovieResponse
import com.android.movie_app.data.MovieResult
import com.android.movie_app.data.Movies
import com.android.movie_app.repository.MovieRepositoryImpl
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryImplTest {

    // Reference Link:- https://www.youtube.com/c/mitrejcevski/videos

    @Mock
    private lateinit var movieResponseObserver: Deferred<MovieResponse>

    @Mock
    private lateinit var movieApi: MovieApi

    private lateinit var movieRepository: MovieRepositoryImpl
    private val movieResultError = MovieResult.Error("No Data Found")
    private val movieResponse = MovieResponse(
        listOf(
            Movies(
                movieId = 508943,
                posterPath = "/jTswp6KyDYKtvC52GbHagrZbGvD.jpg"
            )
        )
    )
    private val movieResult = MovieResult.Success(movieResponse)

    @Before
    fun initialise() {
        movieRepository = MovieRepositoryImpl(movieApi)
    }
    @Test
    fun return_movie_response_success() = runBlocking {

        given(movieApi.getMovieAsync()).willReturn(movieResponseObserver)
        given(movieResponseObserver.await()).willReturn(movieResponse)

        val result = movieRepository.getMovieRepo()
        assertEquals(movieResult,result)
    }

    @Test
    fun return_movie_response_error() = runBlocking {

        val errorResponse = Response.error<MovieResult>(401, ResponseBody.create(null,""))
        given(movieApi.getMovieAsync()).willThrow(HttpException(errorResponse))

        val result = movieRepository.getMovieRepo()
        assertEquals(movieResultError,result)
    }
}