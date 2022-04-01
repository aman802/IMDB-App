package com.example.imdbapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.*
import org.json.JSONArray

class MovieListFragment : Fragment() {

    private lateinit var requestQueue: RequestQueue
    private lateinit var data: ArrayList<Movie>
    private lateinit var recyclerView: RecyclerView
    private lateinit var movieListFragmentInterface: MovieListFragmentInterface

    companion object {
        const val apiUrl = "https://api.themoviedb.org/3/movie/now_playing?api_key=38a73d59546aa378980a88b645f487fc&language=en-US&page=1";
    }

    interface MovieListFragmentInterface {
        fun onItemClick(movie: Movie)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        movieListFragmentInterface = activity as MovieListFragmentInterface;
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)
        requestQueue = Volley.newRequestQueue(context)
        fetchData(view)
        return view;
    }

    private fun fetchData(view: View) {
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, apiUrl, null,
            { response ->
                data = parseData(response.getJSONArray("results"))
                recyclerView = view.findViewById<RecyclerView>(R.id.movie_list).apply {
                    adapter = MovieListAdapter(data) { position ->
                        movieListFragmentInterface.onItemClick(data[position])
                    }
                    layoutManager = LinearLayoutManager(context)
                }
            },
            { error ->
                Log.d("err", error.toString())
            }
        )

        requestQueue.add(jsonObjectRequest)
    }

    private fun parseData(response: JSONArray): ArrayList<Movie> {
        val tempList = ArrayList<Movie>()
        for (i in 0 until response.length()) {
            val item = response.getJSONObject(i)
            tempList.add(Movie(
                item.getString("title"),
                item.getString("poster_path"),
                item.getString("release_date"),
                item.getDouble("vote_average"),
                item.getDouble("popularity"),
                item.getString("overview")
            ))
        }

        return tempList;
    }
}