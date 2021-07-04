package com.monique.projetointegrador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MoviesRvAdapter(private var dataset: List<Movies>): RecyclerView.Adapter<MoviesRvAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var imgMovie: ImageView? = view.findViewById(R.id.imgMovie)
        var titleMovie: TextView? = view.findViewById(R.id.titleMovie)
        var rateMovie: TextView? = view.findViewById(R.id.rateMovie)
        var favIcon: ImageView? = view.findViewById(R.id.notFavIcon)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_movie, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleMovie?.text = dataset[position].getTitle()
        holder.rateMovie?.text = dataset[position].getRate().toString()
        holder.favIcon?.setOnClickListener {
            if(dataset[position].getIsFavorite()){
                it.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)
                dataset[position].setIsFavorite(false)
            }else{
                it.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
                dataset[position].setIsFavorite(true)
            }
        }
    }

    override fun getItemCount() = dataset.size

    fun changeDataSet(newDataSet: List<Movies>){
        dataset = newDataSet
        notifyDataSetChanged()
    }
}