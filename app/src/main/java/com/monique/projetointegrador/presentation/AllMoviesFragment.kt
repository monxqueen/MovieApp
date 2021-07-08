package com.monique.projetointegrador.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.monique.projetointegrador.R
import com.monique.projetointegrador.data.model.Movies
import com.monique.projetointegrador.presentation.adapter.GenresRvAdapter
import com.monique.projetointegrador.presentation.adapter.MoviesRvAdapter

class AllMoviesFragment : Fragment() {

    private lateinit var moviesAdapter: MoviesRvAdapter
    private lateinit var genresAdapter: GenresRvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val genresList = listOf("Ação", "Terror", "Romance", "Animação", "infantil", "Comedia")
        val moviesList = listOf(
            Movies(title = "Moana", rating = "98", img = "https://images-na.ssl-images-amazon.com/images/I/A1JOaV3B6fL._AC_SY679_.jpg"),
            Movies(title = "Frozen", rating = "50", img = "https://images-na.ssl-images-amazon.com/images/I/81zbSEXnDOL.jpg"),
            Movies(title = "Barbie", rating = "100", img = "https://images-na.ssl-images-amazon.com/images/I/51MzRRo9tbL._AC_.jpg")
        )

        val rvMovies = view.findViewById<RecyclerView>(R.id.rvMovies)
        val rvGenres = view.findViewById<RecyclerView>(R.id.rvGenres)

        genresAdapter = GenresRvAdapter(view.context, genresList)
        moviesAdapter = MoviesRvAdapter(view.context, moviesList)

        rvMovies.adapter = moviesAdapter
        rvGenres.adapter = genresAdapter

        rvGenres.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        rvMovies.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)

    }

    companion object {
        @JvmStatic
        fun newInstance() = AllMoviesFragment()
    }
}