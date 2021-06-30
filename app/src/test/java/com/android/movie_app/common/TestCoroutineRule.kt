package com.android.movie_app.common

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
class TestCoroutineRule(
    val testCoroutineDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()) : TestWatcher()
{
    // Reference Link:- https://medium.com/androiddevelopers/easy-coroutines-in-android-viewmodelscope-25bffb605471
    override fun starting(description: Description?) {
        super.starting(description)
       Dispatchers.setMain(testCoroutineDispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
    }
}
