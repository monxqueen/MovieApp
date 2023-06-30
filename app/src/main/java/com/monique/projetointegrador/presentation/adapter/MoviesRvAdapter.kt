package com.monique.projetointegrador.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.monique.projetointegrador.data.base.Constants
import com.monique.projetointegrador.databinding.ItemMovieBinding
import com.monique.projetointegrador.domain.model.Movie
import com.monique.projetointegrador.presentation.ClickListener

internal class MoviesRvAdapter(
    val context: Context,
    private val listener: ClickListener? = null
): ListAdapter<Movie, MoviesRvAdapter.ViewHolder>(DiffUtil()) {


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding
            .inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(position)
    }

    inner class ViewHolder(private val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindView(position: Int) {
            setupImage(position)
            setupTexts(position)
            setupListeners(position)
            setupButtonChecked(position)
        }

        private fun setupImage(position: Int) {
            if (currentList[position].imgHome !== ""){
                Glide.with(context)
                    .load(Constants.BASE_URL_IMAGE.value + currentList[position].imgHome)
                    .into(binding.imgMovie)
            }
        }

        private fun setupTexts(position: Int) {
            binding.titleMovie.text = currentList[position].title
            binding.rateMovie.text = currentList[position].getRating()
        }

        private fun setupListeners(position: Int) {
            binding.imgMovie.setOnClickListener {
                listener?.openMovieDetails(currentList[position].id)
            }
            binding.favBtn.setOnClickListener {
                listener?.onFavoriteClickedListener(
                    currentList[position],
                    !currentList[position].isFavorite
                )
            }
        }

        private fun setupButtonChecked(position: Int) {
            binding.favBtn.isChecked = currentList[position].isFavorite
        }

    }

    override fun getItemCount() = currentList.size

}

internal class DiffUtil : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}
