package com.monique.projetointegrador.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.monique.projetointegrador.R
import com.monique.projetointegrador.domain.Movie
import com.monique.projetointegrador.presentation.adapter.GenresRvAdapter
import com.monique.projetointegrador.presentation.adapter.MoviesRvAdapter

class SearchMoviesFragment : Fragment(), MovieListener {

    private var movieSearched: String? = null
    private lateinit var moviesAdapter: MoviesRvAdapter
    private lateinit var genresAdapter: GenresRvAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var moviesViewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieSearched = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvMovies = view.findViewById<RecyclerView>(R.id.rvMovies)
        val rvGenres = view.findViewById<RecyclerView>(R.id.rvGenres)
        progressBar = view.findViewById(R.id.loading)

        genresAdapter = GenresRvAdapter(context = view.context, listener = this)
        moviesAdapter = MoviesRvAdapter(context = view.context, listener = this)
        rvMovies.adapter = moviesAdapter
        rvGenres.adapter = genresAdapter

        moviesViewModel = ViewModelProvider(requireActivity()).get(MoviesViewModel::class.java)
        movieSearched?.let{
            val replacedMovie = it.replace(" ", "-")
            val movieUri = replacedMovie.toUri()
            moviesViewModel.searchForMovie(movieUri)
            observeSearchResults()
        }
        observeGenres()

    }

    private fun observeSearchResults(){
        moviesViewModel.searchResultsLiveData.observe(viewLifecycleOwner, { result ->
            result?.let{
                moviesAdapter.dataset.clear()
                moviesAdapter.dataset.addAll(it)
                moviesAdapter.notifyDataSetChanged()
                progressBar.visibility = View.GONE
            }
        })
    }

    private fun observeGenres(){
        moviesViewModel.genreListLiveData.observe(viewLifecycleOwner, { result ->
            result?.let{
                genresAdapter.dataset.addAll(it)
                genresAdapter.notifyDataSetChanged()
            }
        })
    }

    override fun openMovieDetails(movieId: Int) {
        val intent = Intent(requireContext(), MovieDetailsActivity::class.java)
        intent.putExtra("MOVIE_ID", movieId)
        startActivity(intent)
    }

    override fun loadMoviesWithGenre(genreIds: List<Int>) {
        moviesViewModel.searchResultsLiveData.observe(viewLifecycleOwner, { result ->
            result?.let { movies ->
                val movieList = mutableListOf<Movie>()
                movies.forEach { movie ->
                    if (movie.genreIds.containsAll(genreIds)) {
                        movieList.add(movie)
                    }
                }
                moviesAdapter.dataset.clear()
                moviesAdapter.dataset.addAll(movieList)
                moviesAdapter.notifyDataSetChanged()
            }
        })
    }

    override fun onFavoriteClickedListener(movie: Movie, isChecked: Boolean) {
        if(isChecked){
            movie.isFavorite = true
            moviesViewModel.favoriteMovie(movie)
        }else{
            movie.isFavorite = false
            moviesViewModel.unfavoriteMovie(movie)
        }
    }

    companion object {
        private const val ARG_PARAM1 = "movieSearched"
        @JvmStatic
        fun newInstance(movieSearched: String) =
            SearchMoviesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, movieSearched)
                }
            }
    }
}