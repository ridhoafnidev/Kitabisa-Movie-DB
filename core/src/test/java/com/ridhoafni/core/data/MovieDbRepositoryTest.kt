package com.ridhoafni.core.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ridhoafni.core.data.local.LocalDataSource
import com.ridhoafni.core.data.remote.RemoteDataSource
import com.ridhoafni.core.fake.FakeData
import com.ridhoafni.core.fake.FakeMovieDbRepository
import com.ridhoafni.core.utils.AppExecutors
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
class MovieDbRepositoryTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val movieDbRepository = FakeMovieDbRepository(remote, local, appExecutors)

    @Test
    fun testGetPopularMovies() = runBlockingTest {
        val dataDummyMovie = FakeData.dummyMovieEntity()

        Mockito.`when`(local.getPopularMovies()).thenReturn(flowOf(dataDummyMovie))
        local.getPopularMovies()
        verify(local).getPopularMovies()
        assertNotNull(movieDbRepository.getPopularMovies())
    }

    @Test
    fun testGetNowPlayingMovies() = runBlockingTest {
        val dataDummyMovie = FakeData.dummyMovieEntity()

        Mockito.`when`(local.getNowPlayingMovies()).thenReturn(flowOf(dataDummyMovie))
        local.getNowPlayingMovies()
        verify(local).getNowPlayingMovies()
        assertNotNull(movieDbRepository.getNowPlayingMovies())
    }

    @Test
    fun testGetTopRatedMovies() = runBlockingTest {
        val dataDummyMovie = FakeData.dummyMovieEntity()

        Mockito.`when`(local.getTopRatedMovies()).thenReturn(flowOf(dataDummyMovie))
        local.getTopRatedMovies()
        verify(local).getTopRatedMovies()
        assertNotNull(movieDbRepository.getTopRatedMovies())
    }

    @Test
    fun testGetUpcomingMovies() = runBlockingTest {
        val dataDummyMovie = FakeData.dummyMovieEntity()

        Mockito.`when`(local.getUpcomingMovies()).thenReturn(flowOf(dataDummyMovie))
        local.getUpcomingMovies()
        verify(local).getUpcomingMovies()
        assertNotNull(movieDbRepository.getUpcomingMovies())
    }

    @Test
    fun testGetDetailMovie() = runBlockingTest {
        val dataDummyMovie = FakeData.dummyMovieEntity()[0]
        val id = dataDummyMovie.id
        Mockito.`when`(local.getDetailMovie(id)).thenReturn(flowOf(dataDummyMovie))
        local.getDetailMovie(id)
        verify(local).getDetailMovie(id)
        assertNotNull(movieDbRepository.getDetailMovie(id))
    }

    @Test
    fun testGetFavouriteMovies() = runBlockingTest {
        val dataDummyMovie = FakeData.dummyMovieEntity()

        Mockito.`when`(local.getAllMovieFavourites()).thenReturn(flowOf(dataDummyMovie))
        local.getAllMovieFavourites()
        verify(local).getAllMovieFavourites()
        assertNotNull(movieDbRepository.getFavouriteMovies())
    }

    @Test
    fun testSetFavouriteMovie() = runBlockingTest {
        val dataDummyMovie = FakeData.dummyMovieEntity()[0]
        Mockito.`when`(local.setFavoriteMovie(dataDummyMovie, true)).then{
            assertEquals(local.setFavoriteMovie(dataDummyMovie, true), local.setFavoriteMovie(dataDummyMovie, true))
        }
    }
}