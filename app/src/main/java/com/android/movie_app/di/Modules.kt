package com.android.movie_app.di

import com.android.movie_app.BuildConfig
import com.android.movie_app.api.MovieApi
import com.android.movie_app.api.createNetworkClient
import com.android.movie_app.repository.MovieRepositoryImpl
import com.android.movie_app.ui.MovieActivityVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit

fun initialiseFeature() = loadFeature

val loadFeature by lazy {
    loadKoinModules(
        listOf(
            repositoryModule,
            viewModelModule,
            apiModule
        )
    )
}

val viewModelModule = module {
    viewModel { MovieActivityVM(movieRepository = get()) }
}

val repositoryModule = module {
    single { MovieRepositoryImpl(movieApi = get()) }
}
val apiModule = module {
    single { movieApi }
}

private val retrofit: Retrofit = createNetworkClient(BuildConfig.BASE_URL)

private val movieApi: MovieApi = retrofit.create(MovieApi::class.java)