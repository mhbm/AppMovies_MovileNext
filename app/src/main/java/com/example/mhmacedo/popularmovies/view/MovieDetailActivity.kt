package com.example.mhmacedo.popularmovies.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.mhmacedo.popularmovies.R
import com.example.mhmacedo.popularmovies.adapter.MovieAdapter
import com.example.mhmacedo.popularmovies.model.Movie
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    lateinit var movieChoose: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)


        movieChoose = intent.extras!!.get(MainActivity.EXTRA_MOVIE) as Movie


        putInformation()


    }

    private fun putInformation() {
        tvMovieTitle.text = movieChoose.title
        tv_MovieVoteAverage.text = "Vote Average: " + movieChoose.vote_average
        tv_Movie_ReleaseDate.text = movieChoose.release_date
        tv_MovieOverview.text = movieChoose.overview

        val url = MovieAdapter.ViewHolder.BASE_URL + MovieAdapter.ViewHolder.SIZE_IMAGE + "/" + movieChoose.poster_path;

        Glide.with(this)
            .load(url)
            //   .apply(RequestOptions().override(300, 169))
            .into(iv_MovieDetail)

    }

}