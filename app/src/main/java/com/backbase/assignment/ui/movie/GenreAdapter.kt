package com.backbase.assignment.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.backbase.assignment.R
import com.backbase.assignment.data.model.Genre

class GenreAdapter(private var genreList : List<Genre>?) : Adapter<GenreAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.genre_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = genreList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.genreList?.get(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var genreText: TextView

        fun bind(item: Genre?) = with(itemView) {
            genreText = itemView.findViewById(R.id.genre)
            genreText.text = item?.name
        }
    }
}