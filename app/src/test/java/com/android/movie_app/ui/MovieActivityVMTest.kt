package com.android.movie_app.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.android.movie_app.api.MovieApi
import com.android.movie_app.common.TestCoroutineRule
import com.android.movie_app.data.MovieResponse
import com.android.movie_app.data.MovieResult
import com.android.movie_app.data.Movies
import com.android.movie_app.repository.MovieRepositoryImpl
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.inOrder
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieActivityVMTest {

    // Reference Link:- https://www.youtube.com/c/mitrejcevski/videos

    @get:Rule
    val task = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var movieResponseObserver: Deferred<MovieResponse>

    @Mock
    private lateinit var movieApi: MovieApi

    @Mock
    private lateinit var movieLiveDataObserver: Observer<MovieResult>
    private val movieResponse = MovieResponse(
        listOf(
            Movies(
                movieId = 508943,
                posterPath = "/jTswp6KyDYKtvC52GbHagrZbGvD.jpg"
            )
        )
    )
    private val enableLoading = MovieResult.Loading(true)
    private val movieResult = MovieResult.Success(movieResponse)
    private val disableLoading = MovieResult.Loading(false)
    private lateinit var movieActivityVM: MovieActivityVM

    @Before
    fun initialise() {
        val movieRepository = MovieRepositoryImpl(movieApi)
        movieActivityVM = MovieActivityVM(movieRepository)
    }

    @Test
    fun movie_feature() = testCoroutineRule.testCoroutineDispatcher.runBlockingTest {

        given(movieApi.getMovieAsync()).willReturn(movieResponseObserver)
        given(movieResponseObserver.await()).willReturn(movieResponse)
        movieActivityVM.movieLiveData().observeForever(movieLiveDataObserver)
        movieActivityVM.getMovies()

        val inOrder = inOrder(movieLiveDataObserver)
        inOrder.verify(movieLiveDataObserver).onChanged(enableLoading)
        inOrder.verify(movieLiveDataObserver).onChanged(movieResult)
        inOrder.verify(movieLiveDataObserver).onChanged(disableLoading)
    }
}