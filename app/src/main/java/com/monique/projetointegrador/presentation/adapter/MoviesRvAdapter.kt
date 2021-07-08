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
import com.monique.projetointegrador.data.model.Movies

class MoviesRvAdapter(val context: Context, private var dataset: List<Movies>): RecyclerView.Adapter<MoviesRvAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var imgMovie: ImageView? = view.findViewById(R.id.imgMovie)
        var titleMovie: TextView? = view.findViewById(R.id.titleMovie)
        var rateMovie: TextView? = view.findViewById(R.id.rateMovie)
        var favBtn: ToggleButton = view.findViewById(R.id.favBtn)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_movie, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(dataset[position].getImg() !== ""){
            holder.imgMovie?.let { Glide.with(context).load(dataset[position].getImg()).into(it) }
        }
        holder.titleMovie?.text = dataset[position].getTitle()
        holder.rateMovie?.text = dataset[position].getRating().toString()
        holder.favBtn.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                dataset[position].setIsFavorite(true)
            }else{
                dataset[position].setIsFavorite(false)
            }
        }
    }

    override fun getItemCount() = dataset.size

    fun changeDataSet(newDataSet: List<Movies>){
        dataset = newDataSet
        notifyDataSetChanged()
    }
}