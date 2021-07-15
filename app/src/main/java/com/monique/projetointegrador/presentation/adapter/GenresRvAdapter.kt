package com.monique.projetointegrador.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.monique.projetointegrador.R
import com.monique.projetointegrador.data.model.Genres

class GenresRvAdapter(val context: Context, val dataset: MutableList<Genres> = mutableListOf()): RecyclerView.Adapter<GenresRvAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val genre: Chip? = view.findViewById(R.id.itemGenre)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_genre, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.genre?.text = dataset[position].name
        holder.genre?.setOnCheckedChangeListener { chip, isChecked ->
            // Responds to chip checked/unchecked
        }

    }

    override fun getItemCount() = dataset.size
}