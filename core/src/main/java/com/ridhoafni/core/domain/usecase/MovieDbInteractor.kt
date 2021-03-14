package com.ridhoafni.core.domain.usecase

import com.ridhoafni.core.data.Resource
import com.ridhoafni.core.domain.model.Movie
import com.ridhoafni.core.domain.repository.IMovieDbRepository
import kotlinx.coroutines.flow.Flow

class MovieDbInteractor(private val movieDbRepository: IMovieDbRepository) : MovieDbUseCase {
    override fun getPopularMovies(): Flow<Resource<List<Movie>>> = movieDbRepository.getPopularMovies()
    override fun getNowPlayingMovies(): Flow<Resource<List<Movie>>> = movieDbRepository.getNowPlayingMovies()
    override fun getTopRatedMovies(): Flow<Resource<List<Movie>>> = movieDbRepository.getTopRatedMovies()
    override fun getUpcomingMovies(): Flow<Resource<List<Movie>>> = movieDbRepository.getUpcomingMovies()
    override fun getDetailMovie(id: Int): Flow<Resource<Movie>> = movieDbRepository.getDetailMovie(id)
    override fun getFavouriteMovies(): Flow<List<Movie>> = movieDbRepository.getFavouriteMovies()
    override fun setFavouriteMovie(movie: Movie, state: Boolean) = movieDbRepository.setFavouriteMovie(movie, state)
}