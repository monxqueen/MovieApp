package com.monique.projetointegrador.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.monique.projetointegrador.R
import com.monique.projetointegrador.data.base.Constants
import com.monique.projetointegrador.domain.Movie
import com.monique.projetointegrador.presentation.MovieListener

class MoviesRvAdapter(
    val context: Context,
    private val listener: MovieListener? = null,
    var dataset: MutableList<Movie> = mutableListOf()
): RecyclerView.Adapter<MoviesRvAdapter.ViewHolder>() {

    //private val favoriteMovies: MutableList<Movie> = mutableListOf()

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var imgMovie: ImageView? = view.findViewById(R.id.imgMovie)
        var titleMovie: TextView? = view.findViewById(R.id.titleMovie)
        var rateMovie: TextView? = view.findViewById(R.id.rateMovie)
        var favBtn: ToggleButton? = view.findViewById(R.id.favBtn)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_movie, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(dataset[position].imgHome !== ""){
            holder.imgMovie?.let { Glide.with(context).load(Constants.BASE_URL_IMAGE.value + dataset[position].imgHome).into(it) }
        }
        holder.titleMovie?.text = dataset[position].title
        holder.rateMovie?.text = dataset[position].rating

        holder.imgMovie?.setOnClickListener {
            listener?.openMovieDetails(dataset[position].id)
        }

        holder.favBtn?.isChecked = dataset[position].isFavorite
        holder.favBtn?.setOnCheckedChangeListener { _, isChecked ->
            listener?.onFavoriteClickedListener(dataset[position], isChecked)
        }
        /*if(dataset[position].isFavorite){
            holder.favBtn?.isActivated
        }else{
            holder.favBtn?.isChecked = false
        }
        holder.favBtn?.setOnClickListener {
            //isChecked assigns true or false
            if(dataset[position].isFavorite){
                holder.favBtn?.isChecked = false
                dataset[position].isFavorite = false
                listener?.saveMoviesToFavoriteTab(dataset[position], "remove")
                //notifyDataSetChanged()
            }else{
                holder.favBtn?.isChecked
                dataset[position].isFavorite = true
                listener?.saveMoviesToFavoriteTab(dataset[position], "add")
            }
        }*/

    }

    override fun getItemCount() = dataset.size

}
