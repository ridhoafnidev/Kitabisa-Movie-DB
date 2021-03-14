package com.ridhoafni.kitabisamoviedb.di

import com.ridhoafni.core.domain.usecase.MovieDbInteractor
import com.ridhoafni.core.domain.usecase.MovieDbUseCase
import com.ridhoafni.kitabisamoviedb.view.detail.DetailMovieViewModel
import com.ridhoafni.kitabisamoviedb.view.movie.MovieViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieDbUseCase> { MovieDbInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
}