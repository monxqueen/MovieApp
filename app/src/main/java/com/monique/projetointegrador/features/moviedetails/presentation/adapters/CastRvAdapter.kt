package com.monique.projetointegrador.features.moviedetails.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.common.utils.loadImage
import com.monique.projetointegrador.data.base.Constants
import com.monique.projetointegrador.databinding.ItemCastBinding
import com.monique.projetointegrador.features.moviedetails.domain.model.Cast

class CastRvAdapter(
    val context: Context
): ListAdapter<Cast, CastRvAdapter.ViewHolder>(DiffUtilCast()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCastBinding
            .inflate(LayoutInflater.from(parent.context), parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(position)
    }

    inner class ViewHolder(
        private val binding: ItemCastBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(position: Int) {
            binding.personImg
                .loadImage(
                Constants.BASE_URL_IMAGE.value +
                        currentList[position].profilePath
                )
            binding.personName.text = currentList[position].name
            binding.personRole.text = currentList[position].character
        }
    }

}

class DiffUtilCast : androidx.recyclerview.widget.DiffUtil.ItemCallback<Cast>() {
    override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
        return oldItem.name == newItem.name
    }
    override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
        return oldItem == newItem
    }
}