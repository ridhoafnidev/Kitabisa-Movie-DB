package com.ridhoafni.kitabisamoviedb

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.ridhoafni.core.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

@Suppress("DEPRECATION")
class MainActivityTest {

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoIdlingResource)
    }

    @Test
    fun loadPopularMovie(){
        delayFourSecond()
        onView(withId(R.id.recycler_view_movie)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.recycler_view_movie)).perform(ViewActions.swipeUp())
        onView(withId(R.id.recycler_view_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.tv_movie_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_overview))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_release_date))
        onView(withId(R.id.btn_favorite)).perform(click())
        onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack())
//        delayFourSecond()
    }

    @Test
    fun loadUpcomingMovie(){
        delayTeenSecond()
        onView(withId(R.id.recycler_view_movie)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.category)).perform(click())
        onView(withId(R.id.rippleUpcoming)).perform(click())
        delayTeenSecond()
        onView(withId(R.id.recycler_view_movie)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.recycler_view_movie)).perform(ViewActions.swipeUp())
        onView(withId(R.id.recycler_view_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.tv_movie_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_overview))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_release_date))
        onView(withId(R.id.btn_favorite)).perform(click())
        onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack())
    }

    @Test
    fun loadNowPlayingMovie(){
        delayTeenSecond()
        onView(withId(R.id.recycler_view_movie)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.category)).perform(click())
        onView(withId(R.id.rippleNowPlaying)).perform(click())
        delayTeenSecond()
        onView(withId(R.id.recycler_view_movie)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.recycler_view_movie)).perform(ViewActions.swipeUp())
        onView(withId(R.id.recycler_view_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.tv_movie_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_overview))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_release_date))
        onView(withId(R.id.btn_favorite)).perform(click())
        onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack())
    }

    @Test
    fun loadTopRatedMovie(){
        delayTeenSecond()
        onView(withId(R.id.recycler_view_movie)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.category)).perform(click())
        onView(withId(R.id.rippleTopRated)).perform(click())
        delayTeenSecond()
        onView(withId(R.id.recycler_view_movie)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.recycler_view_movie)).perform(ViewActions.swipeUp())
        onView(withId(R.id.recycler_view_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.tv_movie_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_overview))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_release_date))
        onView(withId(R.id.btn_favorite)).perform(click())
        onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack())
    }

    @Test
    fun loadFavoriteMovie(){
        delayTeenSecond()
        onView(withId(R.id.recycler_view_movie)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.action_favorite)).perform(click())
        onView(withId(R.id.recycler_view_favorite_movie)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.recycler_view_favorite_movie)).perform(ViewActions.swipeUp())
        onView(withId(R.id.recycler_view_favorite_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.tv_movie_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_overview))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_release_date))
        onView(withId(R.id.btn_favorite)).perform(click())
        onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack())
    }

    private fun delayFourSecond() {
        try {
            Thread.sleep(4000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    private fun delayTeenSecond() {
        try {
            Thread.sleep(10000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

}