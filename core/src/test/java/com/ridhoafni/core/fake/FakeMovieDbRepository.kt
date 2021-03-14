package com.ridhoafni.core.fake

import com.ridhoafni.core.data.NetworkBoundResource
import com.ridhoafni.core.data.Resource
import com.ridhoafni.core.data.local.LocalDataSource
import com.ridhoafni.core.data.remote.RemoteDataSource
import com.ridhoafni.core.data.remote.response.ApiResponse
import com.ridhoafni.core.data.remote.response.DataMovie
import com.ridhoafni.core.domain.model.Movie
import com.ridhoafni.core.domain.repository.IMovieDbRepository
import com.ridhoafni.core.utils.AppExecutors
import com.ridhoafni.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FakeMovieDbRepository(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource,
        private val appExecutors: AppExecutors
    ) : IMovieDbRepository {

    override fun getPopularMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<DataMovie>>(appExecutors) {

            override fun loadFromDB(): Flow<List<Movie>> = localDataSource.getPopularMovies().map { DataMapper.mapEntityToDomain(it) }

            override fun shouldFetch(data: List<Movie>?): Boolean =  data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<DataMovie>>> = remoteDataSource.getPopularMovies()

            override suspend fun saveCallResult(data: List<DataMovie>) {
                val movieList = DataMapper.mapResponsesToEntities(data, "popular")
                localDataSource.insertMovies(movieList)
            }

        }.asFlow()

    override fun getNowPlayingMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<DataMovie>>(appExecutors) {

            override fun loadFromDB(): Flow<List<Movie>> = localDataSource.getNowPlayingMovies().map { DataMapper.mapEntityToDomain(it) }

            override fun shouldFetch(data: List<Movie>?): Boolean =  data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<DataMovie>>> = remoteDataSource.getNowPlayingMovies()

            override suspend fun saveCallResult(data: List<DataMovie>) {
                val movieList = DataMapper.mapResponsesToEntities(data, "now playing")
                localDataSource.insertMovies(movieList)
            }

        }.asFlow()

    override fun getTopRatedMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<DataMovie>>(appExecutors) {

            override fun loadFromDB(): Flow<List<Movie>> = localDataSource.getTopRatedMovies().map { DataMapper.mapEntityToDomain(it) }

            override fun shouldFetch(data: List<Movie>?): Boolean =  data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<DataMovie>>> = remoteDataSource.getTopRatedMovies()

            override suspend fun saveCallResult(data: List<DataMovie>) {
                val movieList = DataMapper.mapResponsesToEntities(data, "top rated")
                localDataSource.insertMovies(movieList)
            }

        }.asFlow()

    override fun getUpcomingMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<DataMovie>>(appExecutors) {

            override fun loadFromDB(): Flow<List<Movie>> = localDataSource.getUpcomingMovies().map { DataMapper.mapEntityToDomain(it) }

            override fun shouldFetch(data: List<Movie>?): Boolean =  data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<DataMovie>>> = remoteDataSource.getUpcomingMovies()

            override suspend fun saveCallResult(data: List<DataMovie>) {
                val movieList = DataMapper.mapResponsesToEntities(data, "upcoming")
                localDataSource.insertMovies(movieList)
            }

        }.asFlow()

    override fun getDetailMovie(id: Int): Flow<Resource<Movie>> =
        object :NetworkBoundResource<Movie, DataMovie>(appExecutors) {
            override fun loadFromDB(): Flow<Movie> = localDataSource.getDetailMovie(id).map { DataMapper.mapEntityToDomain(it) }

            override fun shouldFetch(data: Movie?): Boolean = data == null

            override suspend fun createCall(): Flow<ApiResponse<DataMovie>> = remoteDataSource.getDetailMovie(id)

            override suspend fun saveCallResult(data: DataMovie) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.updateMovie(movieList)
            }
        }.asFlow()

    override fun getFavouriteMovies(): Flow<List<Movie>> = localDataSource.getAllMovieFavourites().map { DataMapper.mapEntityToDomain(it) }

    override fun setFavouriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainMovieToMovieEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }

}