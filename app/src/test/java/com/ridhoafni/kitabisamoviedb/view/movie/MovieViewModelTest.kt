package com.ridhoafni.kitabisamoviedb.view.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.verify
import com.ridhoafni.core.data.Resource
import com.ridhoafni.core.domain.usecase.MovieDbUseCase
import com.ridhoafni.kitabisamoviedb.view.fake.FakeData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.mockito.Mockito.`when`
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var movieViewModel: MovieViewModel

    @Mock
    private lateinit var movieDbUseCase: MovieDbUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        movieViewModel = MovieViewModel(movieDbUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun testGetPopularMovies() = runBlockingTest  {

        val dummyMovie = Resource.Success(FakeData.dummyMovie())

        try {
            `when`(movieDbUseCase.getPopularMovies()).thenReturn(flowOf(dummyMovie))

            movieViewModel = MovieViewModel(movieDbUseCase)

            movieViewModel.getPopularMovies().observeForever { result ->
                verify(movieDbUseCase).getPopularMovies()
                assertNotNull(result)
                assertEquals(dummyMovie.data?.size, result.data?.size)
            }
        }catch (exception: Exception) {
            fail()
        }
    }

    @Test
    fun testGetNowPlayingMovies() = runBlockingTest {
        val dummyMovie = Resource.Success(FakeData.dummyMovie())

        try {
            `when`(movieDbUseCase.getNowPlayingMovies()).thenReturn(flowOf(dummyMovie))

            movieViewModel = MovieViewModel(movieDbUseCase)

            movieViewModel.getNowPlayingMovies().observeForever { result ->
                verify(movieDbUseCase).getNowPlayingMovies()
                assertNotNull(result)
                assertEquals(dummyMovie.data?.size, result.data?.size)
            }
        }catch (exception: Exception) {
            fail()
        }
    }

    @Test
    fun testGetTopRatedMovies() = runBlockingTest {
        val dummyMovie = Resource.Success(FakeData.dummyMovie())

        try {
            `when`(movieDbUseCase.getTopRatedMovies()).thenReturn(flowOf(dummyMovie))

            movieViewModel = MovieViewModel(movieDbUseCase)

            movieViewModel.getTopRatedMovies().observeForever { result ->
                verify(movieDbUseCase).getTopRatedMovies()
                assertNotNull(result)
                assertEquals(dummyMovie.data?.size, result.data?.size)
            }
        }catch (exception: Exception) {
            fail()
        }
    }

    @Test
    fun testGetUpcomingMovies() = runBlockingTest {
        val dummyMovie = Resource.Success(FakeData.dummyMovie())

        try {
            `when`(movieDbUseCase.getUpcomingMovies()).thenReturn(flowOf(dummyMovie))

            movieViewModel = MovieViewModel(movieDbUseCase)

            movieViewModel.getUpcomingMovies().observeForever { result ->
                verify(movieDbUseCase).getUpcomingMovies()
                assertNotNull(result)
                assertEquals(dummyMovie.data?.size, result.data?.size)
            }
        }catch (exception: Exception) {
            fail()
        }
    }
}