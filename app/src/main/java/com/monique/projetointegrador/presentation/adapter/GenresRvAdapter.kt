package com.monique.projetointegrador.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.monique.projetointegrador.R
import com.monique.projetointegrador.data.model.Genres
import com.monique.projetointegrador.presentation.MovieListener

class GenresRvAdapter(val context: Context, private val listener: MovieListener? = null, var dataset: MutableList<Genres> = mutableListOf()): RecyclerView.Adapter<GenresRvAdapter.ViewHolder>() {

    private val selectedItems: MutableList<Int> = mutableListOf()

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val genreChip: Chip? = view.findViewById(R.id.itemGenre)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_all_genres, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.genreChip?.text = dataset[position].name
        holder.genreChip?.setOnCheckedChangeListener { chip, isChecked ->
            if(isChecked){
                selectedItems.add(dataset[position].id)
            }else{
                selectedItems.remove(dataset[position].id)
            }
            listener?.loadMoviesWithGenre(selectedItems)
        }

    }

    override fun getItemCount() = dataset.size
}