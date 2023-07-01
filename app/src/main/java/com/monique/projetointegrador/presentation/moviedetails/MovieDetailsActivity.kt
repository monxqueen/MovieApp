package com.monique.projetointegrador.presentation.moviedetails

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.imageview.ShapeableImageView
import com.monique.projetointegrador.R
import com.monique.projetointegrador.data.base.Constants
import com.monique.projetointegrador.databinding.ActivityMovieDetailsBinding
import com.monique.projetointegrador.domain.model.MovieDetail
import com.monique.projetointegrador.presentation.GeneralErrorActivity
import com.monique.projetointegrador.presentation.adapter.CastRvAdapter
import com.monique.projetointegrador.presentation.adapter.MovieDetailsGenresRvAdapter
import com.monique.projetointegrador.presentation.model.ViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var castRvAdapter: CastRvAdapter
    private lateinit var genresRvAdapter: MovieDetailsGenresRvAdapter
    private val viewModel: MovieDetailsViewModel by viewModel()
    private lateinit var binding: ActivityMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieId = intent.extras?.getInt(MOVIE_ID)
        movieId?.let {
            viewModel.getMovieDetails(it)
        }

        observeMovieDetails()
        observeViewState()

        binding.returnBtn.setOnClickListener { finish() }
    }

    private fun observeViewState(){
        viewModel.viewStateLiveData.observe(this) { result ->
            if (result == ViewState.GeneralError) {
                val intent = Intent(this, GeneralErrorActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun observeMovieDetails(){
        viewModel.movieLiveData.observe(this) { result ->
            result?.let {
                showMovie(it)
            }
        }
    }

    private fun showMovie(movie: MovieDetail){
        genresRvAdapter = MovieDetailsGenresRvAdapter()
        binding.genreRvMovieInfo.adapter = genresRvAdapter
        genresRvAdapter.submitList(movie.genres)

        castRvAdapter = CastRvAdapter(this)
        binding.castRv.adapter = castRvAdapter

        viewModel.getCast(movie.id)
        observeCastOfMovie()

        movie.backdropPath?.let {
            Glide.with(this)
                .load(Constants.BASE_URL_IMAGE.value + movie.backdropPath)
                .into(binding.posterMovie)
        }
        movie.overview?.let {
            binding.movieSynopsis.text = it
        }
        movie.runtime?.let {
            binding.movieDuration.text = movie.getRuntime()
        }

        binding.movieTitle.text = movie.title
        binding.ratingMovieInfoAct.text = movie.getRating()

        if (movie.isFavorite) {
            binding.favBtnMovieInfo.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            binding.favBtnMovieInfo.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }

        binding.movieYear.text = movie.getReleaseYear()

        viewModel.getCertification(movie.id)
        observeCertification()
    }

    private fun observeCertification() {
        viewModel.certificationLiveData.observe(this) { result ->
            result?.let {
                it.forEach { parental_guidance ->
                    binding.ageRestriction.text = ("PG-" + parental_guidance.certification)
                }
            }
        }
    }

    private fun observeCastOfMovie(){
        viewModel.castLiveData.observe(this) { result ->
            result?.let {
                castRvAdapter.submitList(it)
            }
        }
    }

    companion object {
        const val MOVIE_ID = "MOVIE_ID"
    }
}
