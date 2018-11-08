package com.example.mhmacedo.popularmovies.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.mhmacedo.popularmovies.R
import com.example.mhmacedo.popularmovies.model.Film
import kotlinx.android.synthetic.main.film_item.view.*

class FilmAdapter(
    private val items: List<Film>,
    private val context: Context,
    private val listener: (Film) -> Unit
) : Adapter<FilmAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.film_item, parent, false)
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
            private var BASE_URL = "http://image.tmdb.org/t/p/"
            private var SIZE_IMAGE = "w185/"
        }


        fun bindView(
            item: Film,
            listener: (Film) -> Unit
        ) = with(itemView) {
            //iv_film.setImageDrawable(ContextCompat.getDrawable(context, item.imageResourceId))

            val url = BASE_URL + SIZE_IMAGE + "/" + item.path;
            Glide.with(this)
                .load(url)
                .into(iv_film)


            setOnClickListener { listener(item) }
        }

    }
}