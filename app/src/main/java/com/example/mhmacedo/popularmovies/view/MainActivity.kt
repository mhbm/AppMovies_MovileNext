package com.example.mhmacedo.popularmovies.view

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import com.example.mhmacedo.popularmovies.R
import com.example.mhmacedo.popularmovies.adapter.FilmAdapter
import com.example.mhmacedo.popularmovies.model.Film
import com.example.mhmacedo.popularmovies.model.FilmListResult
import com.example.mhmacedo.popularmovies.retriever.FilmRetriever
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.longToast
import org.jetbrains.anko.yesButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private val filmRetriever = FilmRetriever()

    companion object {

    }

    private val callback = object : Callback<FilmListResult> {
        override fun onFailure(call: Call<FilmListResult>, t: Throwable) {
            //Fail
            longToast("Fail loading films")

            Log.e("MainActivity", "Problem calling Github API", t)
            Log.d("MainActivity", "Fail on URL:${call.request()?.url()}")

        }

        override fun onResponse(call: Call<FilmListResult>, response: Response<FilmListResult>) {
            //Success

            longToast("Load finished." + response.isSuccessful)

            if (response.isSuccessful) {
                response.body()?.results?.let {
                    val resultList = response.body()?.results ?: emptyList()
                    recyclerView.adapter =
                            FilmAdapter(
                                resultList,
                                this@MainActivity
                            ) {
                                longToast("Clicked ItemXXXX: $it")
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

    private fun loadDefaultRecyclerView() {
        val gridManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = gridManager


        recyclerView.adapter = FilmAdapter(
            recyclerViewItems(),
            this
        ) {
            longToast("Clicked item: $it.title")
            filmRetriever.getFilmTopRated(
                callback
            )
        }


    }

    private fun recyclerViewItems(): List<Film> {


//        val listTopRated = FilmService::listTopRated(FilmRetriever.API_KEY).execute()

        //      longToast("teste$listTopRated")

        filmRetriever.getFilmTopRated(callback)


        /*
        val film = Film(
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


}
