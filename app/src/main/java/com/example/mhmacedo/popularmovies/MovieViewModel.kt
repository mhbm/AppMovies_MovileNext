package com.example.mhmacedo.popularmovies

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.example.mhmacedo.popularmovies.dao.Movie
import com.example.mhmacedo.popularmovies.dao.MovieRepository

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MovieRepository(application)

//    val allMovies = repository.allMovies

    fun insert(movie: Movie) {
        repository.insert(movie)
    }
}