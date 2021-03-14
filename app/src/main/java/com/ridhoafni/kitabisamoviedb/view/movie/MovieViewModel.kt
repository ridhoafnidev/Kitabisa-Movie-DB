package com.ridhoafni.kitabisamoviedb.view.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ridhoafni.core.domain.usecase.MovieDbUseCase

class MovieViewModel(private val movieDbRepository: MovieDbUseCase) : ViewModel()  {
    fun getPopularMovies() = movieDbRepository.getPopularMovies().asLiveData()
    fun getNowPlayingMovies() = movieDbRepository.getNowPlayingMovies().asLiveData()
    fun getTopRatedMovies() = movieDbRepository.getTopRatedMovies().asLiveData()
    fun getUpcomingMovies() = movieDbRepository.getUpcomingMovies().asLiveData()
}