package com.monique.projetointegrador.presentation.popularmovies

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.monique.projetointegrador.R
import com.monique.projetointegrador.domain.model.Movie
import com.monique.projetointegrador.presentation.GeneralErrorActivity
import com.monique.projetointegrador.presentation.moviedetails.MovieDetailsActivity
import com.monique.projetointegrador.presentation.ClickListener
import com.monique.projetointegrador.presentation.MoviesViewModel
import com.monique.projetointegrador.presentation.adapter.GenresRvAdapter
import com.monique.projetointegrador.presentation.adapter.MoviesRvAdapter
import com.monique.projetointegrador.presentation.model.ViewState
import com.monique.projetointegrador.presentation.moviedetails.MovieDetailsActivity.Companion.MOVIE_ID

class PopularMoviesFragment : Fragment(), ClickListener {

    private lateinit var moviesAdapter: MoviesRvAdapter
    private lateinit var genresAdapter: GenresRvAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var moviesViewModel: MoviesViewModel

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

        genresAdapter = GenresRvAdapter(context = view.context, listener = this)
        moviesAdapter = MoviesRvAdapter(context = view.context, listener = this)
        rvMovies.adapter = moviesAdapter
        rvGenres.adapter = genresAdapter

        moviesViewModel = ViewModelProvider(requireActivity()).get(MoviesViewModel::class.java)
        moviesViewModel.getPopularMovies()
        moviesViewModel.getGenres()
        progressBar = view.findViewById(R.id.loading)
        observeGenres()
        observeMovies()
        observeViewState()
    }

    override fun onResume() {
        super.onResume()
        moviesAdapter.notifyDataSetChanged()
    }

    private fun observeMovies(){
        moviesViewModel.movieListLiveData.observe(viewLifecycleOwner, { result ->
            result?.let{
                moviesAdapter.dataset.clear()
                moviesAdapter.dataset.addAll(it)
                moviesAdapter.notifyDataSetChanged()
                progressBar.visibility = View.GONE
            }
        })
    }

    private fun observeGenres(){
        moviesViewModel.genreListLiveData.observe(viewLifecycleOwner,{ result ->
            result?.let{
                genresAdapter.dataset.addAll(it)
                genresAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun observeViewState(){
        moviesViewModel.viewStateLiveData.observe(viewLifecycleOwner, { result ->
            if(result == ViewState.GeneralError){
                val intent = Intent(requireContext(), GeneralErrorActivity::class.java)
                startActivity(intent)
            }
        })
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