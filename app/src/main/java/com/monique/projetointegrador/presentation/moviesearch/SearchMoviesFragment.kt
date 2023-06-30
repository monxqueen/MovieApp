package com.monique.projetointegrador.presentation.moviesearch

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.monique.projetointegrador.R
import com.monique.projetointegrador.databinding.FragmentHomeMoviesBinding
import com.monique.projetointegrador.domain.model.Movie
import com.monique.projetointegrador.presentation.GeneralErrorActivity
import com.monique.projetointegrador.presentation.moviedetails.MovieDetailsActivity
import com.monique.projetointegrador.presentation.ClickListener
import com.monique.projetointegrador.presentation.MoviesViewModel
import com.monique.projetointegrador.presentation.adapter.GenresRvAdapter
import com.monique.projetointegrador.presentation.adapter.MoviesRvAdapter
import com.monique.projetointegrador.presentation.model.ViewState
import com.monique.projetointegrador.presentation.moviedetails.MovieDetailsActivity.Companion.MOVIE_ID

internal class SearchMoviesFragment : Fragment(), ClickListener {

    private var movieSearched: String? = null
    private lateinit var moviesAdapter: MoviesRvAdapter
    private lateinit var genresAdapter: GenresRvAdapter
    private var moviesViewModel = MoviesViewModel()
    private lateinit var binding: FragmentHomeMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieSearched = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loading.visibility = View.VISIBLE

        genresAdapter = GenresRvAdapter(context = view.context, listener = this)
        moviesAdapter = MoviesRvAdapter(context = view.context, listener = this)
        binding.rvMovies.adapter = moviesAdapter
        binding.rvGenres.adapter = genresAdapter

        val movieUri = movieSearched?.toUri()
        if(movieUri != null){
            updateQuery(movieUri)
        }
        observeMovies()
        binding.loading.visibility = View.GONE
    }

    fun updateQuery(query: Uri){
        observeGenres()
        setObservers()
        moviesViewModel.searchForMovie(query)
        moviesViewModel.getGenres()
        binding.movieNotFound.root.visibility = View.GONE
    }

    private fun observeMovies(){
        moviesViewModel.searchResultsLiveData.observe(viewLifecycleOwner) { result ->
            result?.let {
                binding.rvMovies.visibility = View.VISIBLE
                moviesAdapter.submitList(it)
            }
        }
    }

    private fun observeGenres(){
        moviesViewModel.genreListLiveData.observe(viewLifecycleOwner) { result ->
            result?.let {
                genresAdapter.submitList(it)
            }
        }
    }

    private fun setObservers(){
        moviesViewModel.viewStateLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                ViewState.MovieNotFound -> {
                    binding.movieNotFound.root.visibility = View.VISIBLE
                    binding.rvMovies.visibility = View.GONE
                }
                ViewState.GeneralError -> {
                    Toast.makeText(requireContext(), "General error", Toast.LENGTH_LONG).show()
                    val intent = Intent(requireContext(), GeneralErrorActivity::class.java)
                    startActivity(intent)
                }
            }
        }

    }

    override fun openMovieDetails(movieId: Int) {
        val intent = Intent(requireContext(), MovieDetailsActivity::class.java)
        intent.putExtra(MOVIE_ID, movieId)
        startActivity(intent)
    }

    override fun loadMoviesWithGenre(genreIds: List<Int>) {
        moviesViewModel.searchResultsLiveData.observe(viewLifecycleOwner) { result ->
            result?.let { movies ->
                val movieList = mutableListOf<Movie>()
                movies.forEach { movie ->
                    if (movie.genreIds.containsAll(genreIds)) {
                        movieList.add(movie)
                    }
                }
                moviesAdapter.currentList.clear()
                moviesAdapter.submitList(movieList)
            }
        }
    }

    override fun onFavoriteClickedListener(movie: Movie, isChecked: Boolean) {
        if(isChecked){
            movie.isFavorite = true
            moviesViewModel.addToFavorites(movie)
        }else{
            movie.isFavorite = false
            moviesViewModel.removeFromFavorites(movie)
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