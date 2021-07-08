package com.monique.projetointegrador.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.monique.projetointegrador.R

class GenresRvAdapter(val context: Context, private val dataSet: List<String>): RecyclerView.Adapter<GenresRvAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val genre: Chip? = view.findViewById(R.id.itemGenre)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_genre, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.genre?.text = dataSet[position]
        // change chip backgrund color when clicked: unfinished
        holder.genre?.setOnClickListener {
            it.setBackgroundResource(R.color.greenPrimaryColor)
        }
    }

    override fun getItemCount() = dataSet.size
}