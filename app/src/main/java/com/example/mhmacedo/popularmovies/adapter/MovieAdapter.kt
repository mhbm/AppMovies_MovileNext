package com.example.mhmacedo.popularmovies.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mhmacedo.popularmovies.R
import com.example.mhmacedo.popularmovies.dao.Movie
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter(
    private val items: List<Movie>,
    private val context: Context,
    private val listener: (Movie) -> Unit
) : Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bindView(item, listener)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        companion object {
            var BASE_URL = "http://image.tmdb.org/t/p/"
            var SIZE_IMAGE = "w185"
        }


        fun bindView(
            item: Movie,
            listener: (Movie) -> Unit
        ) = with(itemView) {
            //iv_film.setImageDrawable(ContextCompat.getDrawable(context, item.imageResourceId))

            val url = BASE_URL + SIZE_IMAGE + "/" + item.poster_path;
            Glide.with(this)
                .load(url)
                .apply(RequestOptions().override(185, 185))
                .into(iv_film)


            setOnClickListener { listener(item) }
        }

    }
}