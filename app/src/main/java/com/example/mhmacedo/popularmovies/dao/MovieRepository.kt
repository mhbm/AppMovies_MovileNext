package com.example.mhmacedo.popularmovies.dao

import android.app.Application
import android.arch.lifecycle.LiveData
import org.jetbrains.anko.doAsync

class MovieRepository(application: Application) {

    private val movieDao: MovieDao
    val allMovies: LiveData<List<Movie>>

    init {
        val db = MovieRoomDatabase.getDatabase(application)
        movieDao = db!!.movieDao()
        allMovies = movieDao.getAllMovie()

    }

    fun insert(movie: Movie) {
        doAsync {
            movieDao.insert(movie)
        }
    }
}