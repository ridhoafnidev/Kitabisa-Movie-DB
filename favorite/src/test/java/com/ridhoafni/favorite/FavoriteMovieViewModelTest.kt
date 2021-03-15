package com.ridhoafni.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.verify
import com.ridhoafni.core.domain.usecase.MovieDbUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FavoriteMovieViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: FavoriteMovieViewModel

    @Mock
    private lateinit var movieDbUseCase: MovieDbUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = FavoriteMovieViewModel(movieDbUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun testGetFavoriteMovies() = runBlockingTest {

        val dummyMovie = FakeData.dummyMovie()

        try {
            Mockito.`when`(movieDbUseCase.getFavouriteMovies()).thenReturn(flowOf(dummyMovie))

            viewModel = FavoriteMovieViewModel(movieDbUseCase)

            viewModel.getFavoriteMovies().observeForever { result ->
                verify(movieDbUseCase).getFavouriteMovies()
                Assert.assertNotNull(result)
                Assert.assertEquals(dummyMovie.size, result.size)
            }
        }catch (exception: Exception) {
            Assert.fail()
        }
    }
}