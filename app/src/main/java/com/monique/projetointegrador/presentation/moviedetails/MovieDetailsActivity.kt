package com.monique.projetointegrador.presentation.moviedetails

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.imageview.ShapeableImageView
import com.monique.projetointegrador.R
import com.monique.projetointegrador.data.base.Constants
import com.monique.projetointegrador.domain.model.MovieDetail
import com.monique.projetointegrador.presentation.GeneralErrorActivity
import com.monique.projetointegrador.presentation.adapter.CastRvAdapter
import com.monique.projetointegrador.presentation.adapter.MovieDetailsGenresRvAdapter
import com.monique.projetointegrador.presentation.model.ViewState
import com.monique.projetointegrador.presentation.utils.extensions.loadImage

class MovieDetailsActivity : AppCompatActivity(R.layout.activity_movie_details) {

    private lateinit var returnBtn: FloatingActionButton
    private lateinit var posterMovie: ShapeableImageView
    private lateinit var movieRating: AppCompatTextView
    private lateinit var movieTitle: AppCompatTextView
    private lateinit var movieYear: AppCompatTextView
    private lateinit var ageRestriction: AppCompatTextView
    private lateinit var movieDuration: AppCompatTextView
    private lateinit var favButton: AppCompatImageView
    private lateinit var genresRv: RecyclerView
    private lateinit var movieSynopsis: AppCompatTextView
    private lateinit var castRv: RecyclerView
    private lateinit var genresRvAdapter: MovieDetailsGenresRvAdapter
    private val viewModel = MovieDetailsViewModel()
    private val castRvAdapter by lazy { CastRvAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        posterMovie = findViewById(R.id.posterMovie)
        movieTitle = findViewById(R.id.movieTitle)
        movieRating = findViewById(R.id.ratingMovieInfoAct)
        movieYear = findViewById(R.id.movieYear)
        ageRestriction = findViewById(R.id.ageRestriction)
        movieDuration = findViewById(R.id.movieDuration)
        favButton = findViewById(R.id.favBtnMovieInfo)
        genresRv = findViewById(R.id.genreRvMovieInfo)
        movieSynopsis = findViewById(R.id.movieSynopsis)
        castRv = findViewById(R.id.castRv)
        returnBtn = findViewById(R.id.returnBtn)

        val movieId = intent.extras?.getInt(MOVIE_ID)

        viewModel.getMovieDetails(movieId!!)
        observeMovieDetails()
        observeViewState()

        returnBtn.setOnClickListener { finish() }
    }

    private fun observeViewState() {
        viewModel.viewStateLiveData.observe(this) { result ->
            if (result == ViewState.GeneralError) {
                val intent = Intent(this, GeneralErrorActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun observeMovieDetails() {
        viewModel.movieLiveData.observe(this) { result ->
            result?.let {
                showMovie(it)
            }
        }
    }

    private fun showMovie(movie: MovieDetail) {
        genresRvAdapter = MovieDetailsGenresRvAdapter(movie.genres)
        genresRv.adapter = genresRvAdapter
        castRv.adapter = castRvAdapter

        viewModel.getCast(movie.id)
        observeCastOfMovie()

        with(movie) {
            posterMovie.loadImage(Constants.BASE_URL_IMAGE.value + backdropPath)
            movieSynopsis.text = overview
            movieDuration.text = getRuntime()
            movieTitle.text = title
            movieRating.text = getRating()
            favButton.setImageResource(
                if (isFavorite) R.drawable.ic_baseline_favorite_24
                else R.drawable.ic_baseline_favorite_border_24
            )
            movieYear.text = getReleaseYear()
        }

        viewModel.getCertification(movie.id)
        observeCertification()
    }

    @SuppressLint("SetTextI18n")
    private fun observeCertification() {
        viewModel.certificationLiveData.observe(this) { result ->
            result?.let {
                it.forEach { parentalGuidance ->
                    ageRestriction.text = ("PG-" + parentalGuidance.certification)
                }
            }
        }
    }

    private fun observeCastOfMovie() {
        viewModel.castLiveData.observe(this) { result ->
            castRvAdapter.submitList(result)
        }
    }

    companion object {
        const val MOVIE_ID = "MOVIE_ID"
    }
}
