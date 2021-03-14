package com.ridhoafni.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ridhoafni.core.data.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDbDatabase : RoomDatabase() {
    abstract fun movieDbMovieDao(): MovieDbDao
}