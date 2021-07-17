package com.monique.projetointegrador.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.monique.projetointegrador.R
import com.monique.projetointegrador.data.model.Genres
import com.monique.projetointegrador.data.model.Movies
import com.monique.projetointegrador.presentation.adapter.GenresRvAdapter
import com.monique.projetointegrador.presentation.adapter.MoviesRvAdapter

class FavoriteMoviesFragment : Fragment() { /*se for utilizar a interface de fav movies, coloca FavMoviesListListener como parâmetro aqui e recebe a lista de filmes fav da fragment all movies*/

    private lateinit var moviesAdapter: MoviesRvAdapter
    private var favMoviesList: MutableList<Movies> = mutableListOf()

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

        val genresList: MutableList<Genres> = mutableListOf()

        val rvGenres = view.findViewById<RecyclerView>(R.id.rvGenres)
        genresAdapter = GenresRvAdapter(context = view.context, dataset = genresList)
        rvGenres.adapter = genresAdapter
        rvGenres.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        val rvMovies = view.findViewById<RecyclerView>(R.id.rvMovies)
        moviesAdapter = MoviesRvAdapter(context = view.context, dataset = favMoviesList)
        rvMovies.adapter = moviesAdapter
        rvMovies.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)

    }

    /*override fun addToFavorite(element: Movies){
        favMoviesList.add(element)
        Toast.makeText(requireContext(), "Filme adicionado aos favoritos", Toast.LENGTH_LONG).show()
    }

    override fun removeFromFavorite(position: Int){
        favMoviesList.removeAt(position)
    }

    override fun elementIsFavorite(element: Movies): Boolean{
        return favMoviesList.contains(element)
    }*/
}