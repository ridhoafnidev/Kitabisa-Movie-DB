package com.ridhoafni.favorite.di

import com.ridhoafni.favorite.FavoriteMovieViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteMovieViewModel(get()) }
}