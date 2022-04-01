package com.example.imdbapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity(), MovieListFragment.MovieListFragmentInterface, MovieDetails.MovieDetailsInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showFragment(MovieListFragment())
    }

    private fun showFragment(fragment: Fragment) {
        val manager = supportFragmentManager.beginTransaction()
        manager.replace(R.id.frame_layout, fragment)
        manager.commit()
    }

    override fun onItemClick(movie: Movie) {
        val movieDetailsFragment = MovieDetails()
        val bundle = Bundle()
        bundle.putSerializable("movie", movie)
        movieDetailsFragment.arguments = bundle
        showFragment(movieDetailsFragment)
    }

    override fun onBackClick() {
        showFragment(MovieListFragment())
    }
}