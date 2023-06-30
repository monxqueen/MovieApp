package com.monique.projetointegrador.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.monique.projetointegrador.R
import com.monique.projetointegrador.databinding.ItemAllGenresBinding
import com.monique.projetointegrador.domain.model.Genre
import com.monique.projetointegrador.domain.model.Movie
import com.monique.projetointegrador.presentation.ClickListener

internal class GenresRvAdapter(
    val context: Context,
    private val listener: ClickListener? = null,
    var dataset: MutableList<Genre> = mutableListOf()
): ListAdapter<Genre, GenresRvAdapter.ViewHolder>(DiffUtilGenre()) {

    private val selectedItems: MutableList<Int> = mutableListOf()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAllGenresBinding
            .inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViews(position)
    }

    override fun getItemCount() = currentList.size

    inner class ViewHolder(
        private val binding: ItemAllGenresBinding
    ) : RecyclerView.ViewHolder(binding.root){

        fun bindViews(position: Int) {
            binding.itemGenre.text = currentList[position].name

            binding.itemGenre.setOnClickListener {
                if (selectedItems.contains(currentList[position].id)) {
                    selectedItems.remove(currentList[position].id)
                } else {
                    selectedItems.add(currentList[position].id)
                }
                listener?.loadMoviesWithGenre(selectedItems)
            }
        }
    }
}

internal class DiffUtilGenre : DiffUtil.ItemCallback<Genre>() {
    override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
        return oldItem == newItem
    }
}