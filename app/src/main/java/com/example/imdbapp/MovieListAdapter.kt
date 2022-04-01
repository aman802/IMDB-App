package com.example.imdbapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieListAdapter(private val list: List<Movie>,private val onItemClicked: (position: Int) -> Unit) : RecyclerView.Adapter<MovieListAdapter.CustomViewHolder>() {

    companion object {
        const val BASEURL = "https://image.tmdb.org/t/p/w500";
    }

    class CustomViewHolder(view: View, val onItemClicked: (position: Int) -> Unit) : RecyclerView.ViewHolder(view), View.OnClickListener {
        init {
            view.setOnClickListener(this)
        }

        val rootView = view
        val imageView: ImageView = view.findViewById(R.id.item_movie_image_view)
        val nameTextView: TextView = view.findViewById(R.id.item_movie_name)
        val overviewTextView: TextView = view.findViewById(R.id.item_movie_description)

        override fun onClick(v: View?) {
            val position = adapterPosition
            onItemClicked(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return CustomViewHolder(view, onItemClicked)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val currMovie = list[position]
        holder.nameTextView.text = currMovie.name
        holder.overviewTextView.text = currMovie.overview
        val imageUrl = BASEURL + currMovie.imgUrl
        Glide.with(holder.rootView).load(imageUrl).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return list.size;
    }
}