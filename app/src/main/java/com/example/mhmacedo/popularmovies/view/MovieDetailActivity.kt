package com.example.mhmacedo.popularmovies.view

import android.annotation.TargetApi
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.mhmacedo.popularmovies.R
import com.example.mhmacedo.popularmovies.adapter.MovieAdapter
import com.example.mhmacedo.popularmovies.dao.DbWorkerThread
import com.example.mhmacedo.popularmovies.dao.Movie
import com.example.mhmacedo.popularmovies.dao.MovieRoomDatabase
import kotlinx.android.synthetic.main.activity_movie_detail.*
import org.jetbrains.anko.longToast


class MovieDetailActivity : AppCompatActivity() {

    private lateinit var movieChoose: Movie

    private lateinit var mDbWorkerThread: DbWorkerThread
    private var db: MovieRoomDatabase? = null

    private lateinit var movieExistsDb: Movie

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)


        movieChoose = intent.extras!!.get(MainActivity.EXTRA_MOVIE) as Movie


        mDbWorkerThread = DbWorkerThread("dbWorkerThread")
        mDbWorkerThread.start()

        db = MovieRoomDatabase.getDatabase(this)

//        val allMovie = db!!.movieDao().getAllMovie()
//
//
//        if (allMovie != null) {
//            Transformations.map(allMovie) {
//                it.map {
//                    longToast(it.title)
//                }
//            }
//        }

        var movieExistsDb = db!!.movieDao().findMovie(movieChoose.id)

        if (movieExistsDb != null) {
            // longToast("JA EXISTE")

            changeFabBackground(Color.BLACK)

        } else {
            //longToast("NAO EXISTE")
            changeFabBackground(Color.RED)
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

            if (movieExistsDb == null) {
                //Insert into database
                db!!.movieDao().insert(movieTeste)
                changeFabBackground(Color.BLACK)
                longToast("This film added in database")

            } else {
                //Remove into database
                db!!.movieDao().deleteMovie(movieTeste.id)
                changeFabBackground(Color.RED)
                longToast("This film removed in database")
            }

            movieExistsDb = db!!.movieDao().findMovie(movieChoose.id)

        }

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    private fun changeFabBackground(color: Int) {
        var buttonDrawable = fab.background
        buttonDrawable = DrawableCompat.wrap(buttonDrawable)
        //the color is a direct color int and not a color resource
        DrawableCompat.setTint(buttonDrawable, color)
        fab.background = buttonDrawable
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