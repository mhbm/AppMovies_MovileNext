package com.example.mhmacedo.popularmovies.api

import com.example.mhmacedo.popularmovies.dao.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailService {
    @GET("/3/movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") id: String,
        @Query("api_key") api_key: String
    ): Call<Movie>
}