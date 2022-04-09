package com.demo.movieapp.common.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.demo.movieapp.common.model.Movie
import com.demo.movieapp.common.util.SingleLiveEvent

@Dao
interface MovieDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertMovie(movie: Movie)

    @Insert(onConflict = REPLACE)
    suspend fun insertMovie(vararg movie: Movie)

    @Insert(onConflict = REPLACE)
    suspend fun insertMovie(movie: List<Movie>)


    @Query("DELETE FROM Movie")
    suspend fun deleteAll()

    @Query("SELECT * FROM Movie")
    fun getAllMovieFrmDb(): LiveData<List<Movie>>


}