package com.ridhoafni.core.fake

import com.ridhoafni.core.data.local.entity.MovieEntity

object FakeData {
    fun dummyMovieEntity(): List<MovieEntity> =
        listOf(
            MovieEntity(
                464052,
                "Wonder Woman 1984",
                "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
                "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                "2020-12-16",
                7.4,
                "popular",
                false
            )
        )

    }