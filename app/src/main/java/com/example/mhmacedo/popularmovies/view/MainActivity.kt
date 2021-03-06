package com.example.mhmacedo.popularmovies.view

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.mhmacedo.popularmovies.R
import com.example.mhmacedo.popularmovies.adapter.MovieAdapter
import com.example.mhmacedo.popularmovies.dao.Movie
import com.example.mhmacedo.popularmovies.dao.MovieRoomDatabase
import com.example.mhmacedo.popularmovies.model.MovieListResult
import com.example.mhmacedo.popularmovies.retriever.MovieRetriever
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.longToast
import org.jetbrains.anko.yesButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private val movieRetriever = MovieRetriever()

    private var db: MovieRoomDatabase? = null


    companion object {
        const val EXTRA_MOVIE = "EXTRA_MOVIE"
    }

    private val callback = object : Callback<MovieListResult> {
        override fun onFailure(call: Call<MovieListResult>, t: Throwable) {
            //Fail
            longToast("Fail loading films")
            Log.d("MainActivity", "Fail on URL:${call.request()?.url()}")

        }

        override fun onResponse(call: Call<MovieListResult>, response: Response<MovieListResult>) {
            //Success

            //          longToast("Load finished." + response.isSuccessful)

            if (response.isSuccessful) {
                response.body()?.results?.let {
                    val resultList = response.body()?.results ?: emptyList()
                    recyclerView.adapter =
                            MovieAdapter(
                                resultList,
                                this@MainActivity
                            ) { movieChoose ->
                                val intent = Intent(this@MainActivity, MovieDetailActivity::class.java)
                                intent.putExtra(EXTRA_MOVIE, movieChoose)
                                startActivity(intent)
//                                longToast("Clicked ItemXXXX: $it")
                            }
                }
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (isNetworkConnected()) {
            loadDefaultRecyclerView()
        } else {
            alert("Please check your internet connection and try again.", "No internet connection") {
                this.iconResource = android.R.drawable.ic_dialog_alert
                yesButton { }
            }.show()
        }
    }

    private fun isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    private fun loadDefaultRecyclerView(option: Int? = 1) {
        val gridManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = gridManager

        if (option == 3) {

            //TODO 1 - iMPLEMENTATION INFORMATION IN DATABASE
            db = MovieRoomDatabase.getDatabase(this)

            var allMovies = db!!.movieDao().getAllMovie()

            recyclerView.adapter =
                    MovieAdapter(
                        db!!.movieDao().getAllMovie(),
                        this@MainActivity
                    ) { movieChoose ->
                        val intent = Intent(this@MainActivity, MovieDetailActivity::class.java)
                        intent.putExtra(EXTRA_MOVIE, movieChoose)
                        startActivity(intent)
//                                longToast("Clicked ItemXXXX: $it")
                    }


        } else {

            recyclerView.adapter = MovieAdapter(
                recyclerViewItems(option),
                this
            ) {
                //        longToast("Clicked item: $it.title")

                if (option == 1) {
                    movieRetriever.getFilmTopRated(
                        callback
                    )
                } else if (option == 2) {
                    movieRetriever.getFilmPopularMovies(
                        callback
                    )
                }
            }
        }


    }

    private fun recyclerViewItems(option: Int? = 1): List<Movie> {


//        val listTopRated = MovieService::listTopRated(FilmRetriever.API_KEY).execute()

        //      longToast("teste$listTopRated")

        if (option == 1) {
            movieRetriever.getFilmTopRated(callback)
        } else if (option == 2) {
            movieRetriever.getFilmPopularMovies(callback)
        }


        /*
        val movie = Movie(
            "/2uNW4WbgBXL25BAbXGLnLqX71Sw.jpg",
            335983F,
            "Venom",
            "When Eddie Brock acquires the powers of a symbiote, he will have",
            "2018-10-03",
            6.6

        )

        return listOf(film, film, film, film, film, film)
        */
        return emptyList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.movie_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            R.id.menu_topRated -> {
                longToast("Option top rated selected")
                loadDefaultRecyclerView(1)
            }
            R.id.menu_popular -> {
                longToast("Option popular movies")
                loadDefaultRecyclerView(2)
            }
            R.id.menu_yourList -> {
                loadDefaultRecyclerView(3)
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
