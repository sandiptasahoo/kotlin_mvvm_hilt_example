package com.backbase.assignment.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.R
import com.backbase.assignment.data.model.Movie
import com.bumptech.glide.Glide

class NowPlayingMovieAdapter(private var items:  List<Movie>?) :
    RecyclerView.Adapter<NowPlayingMovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.now_playing_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.items?.get(position))
    }

    override fun getItemCount() = items?.size ?: 0

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var poster: ImageView

        fun bind(item: Movie?) = with(itemView) {
            poster = itemView.findViewById(R.id.poster)
            Glide.with(itemView.rootView)
                .load("https://image.tmdb.org/t/p/original${item?.posterPath}")
                .into(poster)
        }
    }
}