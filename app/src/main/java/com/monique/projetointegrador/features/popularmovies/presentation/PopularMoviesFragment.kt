package com.monique.projetointegrador.features.popularmovies.presentation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.monique.projetointegrador.databinding.FragmentHomeMoviesBinding
import com.monique.projetointegrador.domain.model.Movie
import com.monique.projetointegrador.presentation.GeneralErrorActivity
import com.monique.projetointegrador.features.moviedetails.presentation.MovieDetailsActivity
import com.monique.projetointegrador.presentation.ClickListener
import com.monique.projetointegrador.presentation.MoviesViewModel
import com.monique.projetointegrador.presentation.adapter.GenresRvAdapter
import com.monique.projetointegrador.presentation.adapter.MoviesRvAdapter
import com.monique.projetointegrador.presentation.model.ViewState
import com.monique.projetointegrador.features.moviedetails.presentation.MovieDetailsActivity.Companion.MOVIE_ID
import org.koin.androidx.viewmodel.ext.android.viewModel

class PopularMoviesFragment : Fragment(), ClickListener {

    private lateinit var moviesAdapter: MoviesRvAdapter
    private lateinit var genresAdapter: GenresRvAdapter
    private val moviesViewModel: PopularMoviesViewModel by viewModel()

    private lateinit var binding: FragmentHomeMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        genresAdapter = GenresRvAdapter(context = view.context, listener = this)
        moviesAdapter = MoviesRvAdapter(context = view.context, listener = this)
        binding.rvMovies.adapter = moviesAdapter
        binding.rvGenres.adapter = genresAdapter

        moviesViewModel.getPopularMovies()
        moviesViewModel.getGenres()

        binding.loading.visibility = View.VISIBLE

        observeGenres()
        observeMovies()
        observeViewState()
    }

    override fun onResume() {
        super.onResume()
        moviesViewModel.getPopularMovies()
        //moviesAdapter.notifyDataSetChanged()
    }

    private fun observeMovies(){
        moviesViewModel.movieListLiveData.observe(viewLifecycleOwner) { result ->
            result?.let {
                moviesAdapter.submitList(it)
                binding.loading.visibility = View.GONE
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

    private fun observeViewState(){
        moviesViewModel.viewStateLiveData.observe(viewLifecycleOwner) { result ->
            if (result == ViewState.GeneralError) {
                val intent = Intent(requireContext(), GeneralErrorActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun openMovieDetails(movieId: Int){
        val intent = Intent(requireContext(), MovieDetailsActivity::class.java)
        intent.putExtra(MOVIE_ID, movieId)
        startActivity(intent)
    }

    override fun loadMoviesWithGenre(genreIds: List<Int>) {
        moviesViewModel.getMoviesByGenre(genreIds)
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

}