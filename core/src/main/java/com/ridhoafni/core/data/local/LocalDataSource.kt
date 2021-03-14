package com.ridhoafni.core.data.local

import com.ridhoafni.core.data.local.entity.MovieEntity
import com.ridhoafni.core.data.local.room.MovieDbDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource (private val movieDbDao: MovieDbDao) {
    fun getPopularMovies(): Flow<List<MovieEntity>> = movieDbDao.getPopularMovies()
    fun getNowPlayingMovies(): Flow<List<MovieEntity>> = movieDbDao.getNowPlayingMovies()
    fun getTopRatedMovies(): Flow<List<MovieEntity>> = movieDbDao.getTopRatedMovies()
    fun getUpcomingMovies(): Flow<List<MovieEntity>> = movieDbDao.getUpcomingMovies()
    fun getDetailMovie(id: Int): Flow<MovieEntity> = movieDbDao.getDetailMovie(id)
    suspend fun insertMovies(movies: List<MovieEntity>) = movieDbDao.insertMovie(movies)
    fun getAllMovieFavourites(): Flow<List<MovieEntity>> = movieDbDao.getFavoriteMovies()
    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.favorite = newState
        movieDbDao.updateFavoriteMovie(movie)
    }
    suspend fun updateMovie(movie: MovieEntity) = movieDbDao.updateMovie(movie)
}