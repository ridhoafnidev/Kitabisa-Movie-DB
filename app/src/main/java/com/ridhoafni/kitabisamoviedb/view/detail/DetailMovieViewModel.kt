package com.ridhoafni.kitabisamoviedb.view.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ridhoafni.core.domain.model.Movie
import com.ridhoafni.core.domain.usecase.MovieDbUseCase

class DetailMovieViewModel(private val movieDbRepository: MovieDbUseCase) : ViewModel() {
    fun getMovie(id: Int) = movieDbRepository.getDetailMovie(id).asLiveData()
    fun setFavoriteMovie(movie: Movie, state: Boolean) = movieDbRepository.setFavouriteMovie(movie, state)
}