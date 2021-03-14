package com.ridhoafni.core.utils

import com.ridhoafni.core.data.local.entity.MovieEntity
import com.ridhoafni.core.data.remote.response.DataMovie
import com.ridhoafni.core.domain.model.Movie

object DataMapper {

    fun mapResponsesToEntities(input: List<DataMovie>, type: String): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                title = it.title,
                overview = it.overview,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                type = type,
                favorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapResponsesToEntities(input: DataMovie) = MovieEntity(
            id = input.id,
            title = input.title,
            overview = input.overview,
            posterPath = input.posterPath,
            releaseDate = input.releaseDate,
            voteAverage = input.voteAverage,
            favorite = false
    )

    fun mapEntityToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                title = it.title,
                overview = it.overview,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                type = it.type,
                favorite = it.favorite,
            )
        }

    fun mapEntityToDomain(input: MovieEntity) =
            Movie(
                id = input.id,
                title = input.title,
                overview = input.overview,
                posterPath = input.posterPath,
                releaseDate = input.releaseDate,
                voteAverage = input.voteAverage,
                type = input.type,
                favorite = input.favorite,
            )

    fun mapDomainMovieToMovieEntity(input: Movie) =
            MovieEntity(
                id = input.id,
                title = input.title,
                overview = input.overview,
                posterPath = input.posterPath,
                releaseDate = input.releaseDate,
                voteAverage = input.voteAverage,
                type = input.type,
                favorite = input.favorite,
            )
}