package com.monique.projetointegrador.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.monique.projetointegrador.R
import com.monique.projetointegrador.presentation.adapter.GenresRvAdapter

class FavoriteMoviesFragment : Fragment() {

    //private lateinit var moviesAdapter: MoviesRvAdapter

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

        val rvGenres = view.findViewById<RecyclerView>(R.id.rvGenres)
        genresAdapter = GenresRvAdapter(view.context, genresList)
        rvGenres.adapter = genresAdapter
        rvGenres.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        //val rvMovies = view.findViewById<RecyclerView>(R.id.rvMovies)
        /*rvMovies.adapter = moviesRvAdapter
          rvMovies.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
         */
    }
}