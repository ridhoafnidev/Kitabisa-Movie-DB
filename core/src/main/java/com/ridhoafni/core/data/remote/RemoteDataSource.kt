package com.ridhoafni.core.data.remote

import android.util.Log
import com.ridhoafni.core.data.remote.response.ApiResponse
import com.ridhoafni.core.data.remote.response.DataMovie
import com.ridhoafni.core.data.remote.service.ApiService
import com.ridhoafni.core.utils.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getPopularMovies(): Flow<ApiResponse<List<DataMovie>>> {
        EspressoIdlingResource.increment()
        return flow {
            try {
                EspressoIdlingResource.decrement()
                val response = apiService.getPopularMovies(page = 1)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                }else if(dataArray.isEmpty()){
                    emit(ApiResponse.Empty)
                }
            }catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getNowPlayingMovies(): Flow<ApiResponse<List<DataMovie>>> {
        EspressoIdlingResource.increment()
        return flow {
            try {
                EspressoIdlingResource.decrement()
                val response = apiService.getNowPlayingMovies(page = 1)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                }else if(dataArray.isEmpty()){
                    emit(ApiResponse.Empty)
                }
            }catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTopRatedMovies(): Flow<ApiResponse<List<DataMovie>>> {
        EspressoIdlingResource.increment()
        return flow {
            try {
                EspressoIdlingResource.decrement()
                val response = apiService.getTopRatedMovies(page = 1)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                }else if(dataArray.isEmpty()){
                    emit(ApiResponse.Empty)
                }
            }catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getUpcomingMovies(): Flow<ApiResponse<List<DataMovie>>> {
        EspressoIdlingResource.increment()
        return flow {
            try {
                EspressoIdlingResource.decrement()
                val response = apiService.getUpcomingMovies(page = 1)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                }else if(dataArray.isEmpty()){
                    emit(ApiResponse.Empty)
                }
            }catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailMovie(id: Int): Flow<ApiResponse<DataMovie>> {
        EspressoIdlingResource.increment()
        return flow {
            try {
                EspressoIdlingResource.decrement()
                val response = apiService.getMovieDetail(id)
                emit(ApiResponse.Success(response))
            }catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}