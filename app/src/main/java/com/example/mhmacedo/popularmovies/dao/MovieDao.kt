package com.example.mhmacedo.popularmovies.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(movie: Movie)

    @Query("DELETE FROM movie_table ")
    fun deleteAll()

    @Query("SELECT * FROM movie_table")
    fun getAllMovie(): LiveData<List<Movie>>

    @Query("SELECT * FROM movie_table WHERE id = :id LIMIT 1")
    fun findMovie(id: Float): LiveData<List<Movie>>

    @Query("DELETE FROM movie_table WHERE id = :id ")
    fun deleteMovie(id: Float): Int


}