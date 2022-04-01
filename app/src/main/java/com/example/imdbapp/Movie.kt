package com.example.imdbapp

import android.os.Parcel
import java.io.Serializable

data class Movie (
    val name: String,
    val imgUrl: String,
    val releaseDate: String,
    val rating: Double,
    val popularity: Double,
    val overview: String
) : Serializable
