package com.monique.projetointegrador.presentation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.monique.projetointegrador.R
import com.monique.projetointegrador.domain.Movie
import com.monique.projetointegrador.presentation.adapter.GenresRvAdapter
import com.monique.projetointegrador.presentation.adapter.MoviesRvAdapter

class AllMoviesFragment : Fragment(), MovieListener {

    private lateinit var moviesAdapter: MoviesRvAdapter
    private lateinit var genresAdapter: GenresRvAdapter
    private lateinit var progressBar: ProgressBar
    private val viewModel = AllMoviesFragmentViewModel()
    private val movies: MutableList<Movie> = mutableListOf()
    //private lateinit var db: AppDatabase

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

        val rvMovies = view.findViewById<RecyclerView>(R.id.rvMovies)
        val rvGenres = view.findViewById<RecyclerView>(R.id.rvGenres)

        progressBar = view.findViewById(R.id.loading)

        genresAdapter = GenresRvAdapter(context = view.context, listener = this)
        moviesAdapter = MoviesRvAdapter(context = view.context, listener = this)
        rvMovies.adapter = moviesAdapter
        rvGenres.adapter = genresAdapter

        //db = AppDatabase.getInstance(requireContext())

        viewModel.getMovies()
        viewModel.getGenres()

        getGenresToShow()
        getMoviesToShow()
    }

    private fun getMoviesToShow(){
        viewModel.movieListLiveData.observe(viewLifecycleOwner, { response ->
            response?.let{
                movies.addAll(it)
                moviesAdapter.dataset.clear()
                moviesAdapter.dataset.addAll(movies)
                moviesAdapter.notifyDataSetChanged()
                progressBar.visibility = View.GONE
            }

        })
    }

    private fun getGenresToShow(){
        viewModel.genreListLiveData.observe(viewLifecycleOwner,{ response ->
            response?.let{
                genresAdapter.dataset.addAll(it)
                genresAdapter.notifyDataSetChanged()
            }
        })
    }

    override fun openMovieDetails(movieId: Int){
        val intent = Intent(requireContext(), MovieDetailsActivity::class.java)
        intent.putExtra("MOVIE_ID", movieId)
        startActivity(intent)
    }

    override fun loadMoviesWithGenre(genresId: List<Int>) {
        viewModel.getMoviesByGenre(genresId)
        getMoviesToShow()
    }

    /**************************** IMPLEMENTAR *****************************/
    override fun saveMoviesToFavoriteTab(movie: Movie, addOrRemove: String) {
        /*val favoriteMoviesDao = db.favoriteMoviesDao()
        val genreMovieBindingDao = db.genreMovieBindingDao()
        favoriteMoviesDao.insert(movie)
        lateinit var genreMovieBinding: GenreMovieBinding
        for(genreId in movie.genres){
            genreMovieBinding = GenreMovieBinding(
                genreId = genreId,
                movieId = movie.id
            )
            genreMovieBindingDao.insertGenreMovie(genreMovieBinding)
        }*/
        if(addOrRemove == "add"){
            if(!FavoriteMoviesFragment.favMoviesList.contains(movie)){
                FavoriteMoviesFragment.favMoviesList.add(movie)
            }
        }else{
            if(FavoriteMoviesFragment.favMoviesList.contains(movie)){
                FavoriteMoviesFragment.favMoviesList.remove(movie)
            }
        }


        /*val intent = Intent(requireContext(), FavoriteMoviesFragment::class.java)
        intent.putParcelableArrayListExtra("FAVORITE_MOVIES", ArrayList(favoriteMovies))*/
    }

    companion object {
        @JvmStatic
        fun newInstance() = AllMoviesFragment()
    }
}