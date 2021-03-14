package com.ridhoafni.core.data.remote.service

import com.ridhoafni.core.BuildConfig.API_KEY
import com.ridhoafni.core.data.remote.response.DataMovie
import com.ridhoafni.core.data.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String? = API_KEY,
        @Query("language") language: String? = "en-US",
        @Query("page") page: Int
    ): MovieResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") apiKey: String? = API_KEY,
        @Query("language") language: String? = "en-US",
        @Query("page") page: Int
    ): MovieResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String? = API_KEY,
        @Query("language") language: String? = "en-US",
        @Query("page") page: Int
    ): MovieResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String? = API_KEY,
        @Query("language") language: String? = "en-US",
        @Query("page") page: Int
    ): MovieResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String? = API_KEY,
        @Query("language") language: String? = "en-US"
    ): DataMovie

}