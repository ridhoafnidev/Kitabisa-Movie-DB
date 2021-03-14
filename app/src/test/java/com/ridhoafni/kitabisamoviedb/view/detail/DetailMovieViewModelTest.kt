package com.ridhoafni.kitabisamoviedb.view.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.verify
import com.ridhoafni.core.data.Resource
import com.ridhoafni.core.domain.usecase.MovieDbUseCase
import com.ridhoafni.kitabisamoviedb.view.fake.FakeData
import org.junit.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: DetailMovieViewModel

    @Mock
    private lateinit var movieDbUseCase: MovieDbUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = DetailMovieViewModel(movieDbUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun testGetMovie() = runBlockingTest {

        val dummyMovie = Resource.Success(FakeData.dummyMovie()[0])

        val id = dummyMovie.data!!.id

        Mockito.`when`(movieDbUseCase.getDetailMovie(id)).thenReturn(flowOf(dummyMovie))

        viewModel.getMovie(id).observeForever { result ->
            verify(movieDbUseCase).getDetailMovie(id)
            assertEquals(dummyMovie.data?.id, result.data?.id)
            assertEquals(dummyMovie.data?.title, result.data?.title)
            assertEquals(dummyMovie.data?.releaseDate, result.data?.releaseDate)
            assertEquals(dummyMovie.data?.posterPath, result.data?.posterPath)
            assertEquals(
                dummyMovie.data?.voteAverage.toString(),
                result.data?.voteAverage.toString()
            )
        }
    }

    @Test
    fun testSetMovieFavorite(){
        val dataDummyMovie = FakeData.dummyMovie()[0]
        doNothing().`when`(movieDbUseCase).setFavouriteMovie(dataDummyMovie, true)
        viewModel.setFavoriteMovie(dataDummyMovie, true)
        verify(movieDbUseCase).setFavouriteMovie(dataDummyMovie, true)
    }

}
