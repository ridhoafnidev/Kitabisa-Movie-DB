package com.ridhoafni.core.data.local.room

import androidx.room.*
import com.ridhoafni.core.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDbDao {
    @Query("SELECT * FROM movieentities where type = 'popular' ")
    fun getPopularMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movieentities where type = 'upcoming' ")
    fun getUpcomingMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movieentities where type = 'top rated' ")
    fun getTopRatedMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movieentities where type = 'now playing' ")
    fun getNowPlayingMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movieentities where favorite = 1")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movies: List<MovieEntity>)

    @Query("DELETE FROM movieentities")
    suspend fun deleteMovies()

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)

    @Transaction
    @Query("SELECT * FROM movieentities WHERE id = :id")
    fun getDetailMovie(id: Int): Flow<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateMovie(movie: MovieEntity)
}