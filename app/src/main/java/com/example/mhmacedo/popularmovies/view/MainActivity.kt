package com.example.mhmacedo.popularmovies.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.example.mhmacedo.popularmovies.R
import com.example.mhmacedo.popularmovies.adapter.FilmAdapter
import com.example.mhmacedo.popularmovies.model.Film
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.longToast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = FilmAdapter(
            recyclerViewItems(),
            this
        ) {
            longToast("Clicked item: $it.title")
        }

        val gridManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = gridManager
    }

    private fun recyclerViewItems(): List<Film> {
        val film = Film(
            "/2uNW4WbgBXL25BAbXGLnLqX71Sw.jpg",
            335983F,
            "Venom",
            2018,
            "When Eddie Brock acquires the powers of a symbiote, he will have",
            "2018-10-03",
            6.6F

        )

        return listOf(film, film, film, film, film, film)
    }


}
