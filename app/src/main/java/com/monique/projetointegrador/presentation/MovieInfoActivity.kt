package com.monique.projetointegrador.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.imageview.ShapeableImageView
import com.monique.projetointegrador.R
import com.monique.projetointegrador.data.model.Movies

class MovieInfoActivity : AppCompatActivity() {

    private lateinit var returnBtn: FloatingActionButton
    private lateinit var posterMovie: ShapeableImageView
    private lateinit var movieRating: TextView
    private lateinit var movieTitle: TextView
    private lateinit var movieYear: TextView
    private lateinit var ageRestriction: TextView
    private lateinit var movieDuration: TextView
    private lateinit var favButton: ToggleButton
    private lateinit var genresRv: RecyclerView
    private lateinit var movieSynopsis: TextView
    private lateinit var castRv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_info)
        movieTitle = findViewById(R.id.movieTitle)

        val movie = intent.getParcelableExtra("MOVIE_INFO") as Movies?
        movieTitle.text = movie?.getTitle()
        movieRating.text = movie?.showRating()


    }

}