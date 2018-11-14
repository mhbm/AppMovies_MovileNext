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

    @Query("DELETE FROM MOVIE_TABLE ")
    fun deleteAll()

    @Query("SELECT * FROM MOVIE_TABLE ORDER BY id ASC")
    fun getAllMovie(): LiveData<List<Movie>>
}