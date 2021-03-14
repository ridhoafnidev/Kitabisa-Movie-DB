package com.ridhoafni.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ridhoafni.core.domain.usecase.MovieDbUseCase

class FavoriteMovieViewModel(private val movieDbRepository: MovieDbUseCase) : ViewModel() {
    fun getFavoriteMovies() = movieDbRepository.getFavouriteMovies().asLiveData()
}