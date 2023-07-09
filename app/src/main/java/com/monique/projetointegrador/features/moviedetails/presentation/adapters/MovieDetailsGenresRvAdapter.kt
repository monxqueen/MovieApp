package com.monique.projetointegrador.features.moviedetails.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.monique.projetointegrador.databinding.ItemMovieGenresBinding
import com.monique.projetointegrador.domain.model.Genre
import com.monique.projetointegrador.presentation.adapter.DiffUtilGenre

internal class MovieDetailsGenresRvAdapter
    : ListAdapter<Genre, MovieDetailsGenresRvAdapter.ViewHolder>(DiffUtilGenre()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieGenresBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(position)
    }

    inner class ViewHolder(
        private val binding: ItemMovieGenresBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bindView(position: Int) {
            binding.itemGenre.text = currentList[position].name
        }
    }
}