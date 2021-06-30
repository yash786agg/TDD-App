package com.android.movie_app

import android.app.Application
import com.android.movie_app.di.initialiseFeature
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApplication)
            initialiseFeature()
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}