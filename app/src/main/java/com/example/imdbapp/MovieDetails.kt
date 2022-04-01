package com.example.imdbapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class MovieDetails : Fragment() {

    private lateinit var movie: Movie
    private lateinit var movieDetailsInterface: MovieDetailsInterface

    companion object {
        const val BASEURL = "https://image.tmdb.org/t/p/w500";
    }

    interface MovieDetailsInterface {
        fun onBackClick()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        movieDetailsInterface = activity as MovieDetailsInterface
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_movie_details, container, false)

        val bundle = arguments
        if (bundle != null) {
            movie = bundle.getSerializable("movie") as Movie
        }

        val backButton: ImageView = view.findViewById(R.id.back_button)

        backButton.setOnClickListener {
            movieDetailsInterface.onBackClick()
        }

        val movieNameTextView: TextView = view.findViewById(R.id.movie_name)
        val movieImage: ImageView = view.findViewById(R.id.movie_details_image)
        val releaseDate: TextView = view.findViewById(R.id.release_date_text_view)
        val rating: TextView = view.findViewById(R.id.rating_text_view)
        val popularity: TextView = view.findViewById(R.id.popularity_text_view)
        val overview: TextView = view.findViewById(R.id.overview_text_view)

        movieNameTextView.text = movie.name
        releaseDate.text = movie.releaseDate
        rating.text = movie.rating.toString()
        popularity.text = movie.popularity.toString()
        overview.text = movie.overview

        val imageUrl = BASEURL + movie.imgUrl
        Glide.with(view).load(imageUrl).into(movieImage)

        return view;
    }

}