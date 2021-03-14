package com.ridhoafni.core.domain.usecase

import com.ridhoafni.core.data.Resource
import com.ridhoafni.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieDbUseCase {
    fun getPopularMovies(): Flow<Resource<List<Movie>>>
    fun getNowPlayingMovies(): Flow<Resource<List<Movie>>>
    fun getTopRatedMovies(): Flow<Resource<List<Movie>>>
    fun getUpcomingMovies(): Flow<Resource<List<Movie>>>
    fun getDetailMovie(id: Int): Flow<Resource<Movie>>
    fun getFavouriteMovies(): Flow<List<Movie>>
    fun setFavouriteMovie(movie: Movie, state: Boolean)
}