package com.backbase.assignment.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.R
import com.backbase.assignment.data.model.Movie
import com.backbase.assignment.utils.getDateString
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.movie_item.view.*

class PopularMoviesPagerAdapter constructor(private val listener: OnItemClickListener) :
    PagingDataAdapter<Movie, PopularMoviesPagerAdapter.ViewHolder>(DataDifferntiator) {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        fun bind(item: Movie?) = with(itemView) {
            Glide.with(itemView.rootView)
                .load("https://image.tmdb.org/t/p/original${item?.posterPath}")
                .into(itemView.poster)
            itemView.movieTitle.text = item?.title
            itemView.releaseDate.text = item?.releaseDate?.getDateString()
            itemView.ratingProgress.setProgress(
                item?.voteAverage?.toFloat()?.times(0.1f) ?: 0.0f
            )
            itemView.rating.text = "${item?.voteAverage?.toInt()?.times(10) ?: 0.0}%"

            itemView.setOnClickListener{
                listener.onItemClick(item?.id)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.movie_item, parent, false)
        )
    }

    object DataDifferntiator : DiffUtil.ItemCallback<Movie>() {

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    interface OnItemClickListener {
        fun onItemClick(id: Int?)
    }
}