package com.example.mhmacedo.popularmovies.view

import android.arch.lifecycle.Transformations
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.mhmacedo.popularmovies.R
import com.example.mhmacedo.popularmovies.adapter.MovieAdapter
import com.example.mhmacedo.popularmovies.dao.DbWorkerThread
import com.example.mhmacedo.popularmovies.dao.MovieRoomDatabase
import com.example.mhmacedo.popularmovies.model.Movie
import kotlinx.android.synthetic.main.activity_movie_detail.*
import org.jetbrains.anko.longToast

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var movieChoose: Movie

    private lateinit var mDbWorkerThread: DbWorkerThread
    private var db: MovieRoomDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)


        movieChoose = intent.extras!!.get(MainActivity.EXTRA_MOVIE) as Movie


        mDbWorkerThread = DbWorkerThread("dbWorkerThread")
        mDbWorkerThread.start()

        db = MovieRoomDatabase.getDatabase(this)

        val allMovie = db!!.movieDao().getAllMovie()

        val movie  : List<com.example.mhmacedo.popularmovies.dao.Movie>


        if (allMovie != null) {
            Transformations.map(allMovie) {
                it.map {
                    longToast(it.title)
                }
            }
        }

        putInformation()

        fab.setOnClickListener {


            val movieTeste = com.example.mhmacedo.popularmovies.dao.Movie(
                movieChoose.id,
                movieChoose.title,
                movieChoose.overview,
                movieChoose.release_date,
                movieChoose.vote_average,
                movieChoose.poster_path
            )
            db?.movieDao()?.insert(movieTeste)
            /*
            //TODO 1
            Cannot access database on the main thread since it may potentially lock the UI for a long period of time.

             */

           val task = Runnable { db?.movieDao()?.insert(movieTeste) }
            mDbWorkerThread.postTask(task)


        }

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