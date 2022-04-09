package com.demo.movieapp.common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demo.movieapp.common.model.Movie

@Database(
    entities = [Movie::class],
    version = 1
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDoa(): MovieDao
}