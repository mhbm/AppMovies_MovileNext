package com.example.mhmacedo.popularmovies.api


import com.example.mhmacedo.popularmovies.model.MovieListResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("/3/movie/top_rated")
    fun listTopRated(
        @Query("api_key") api_key: String
    ): Call<MovieListResult>
}